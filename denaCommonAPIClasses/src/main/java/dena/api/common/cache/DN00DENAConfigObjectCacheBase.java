package dena.api.common.cache;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import dena.api.common.interfaces.DN00IsCRUDServicesForDENAPersistableObject;
import dena.api.common.interfaces.DN00IsFindServicesForDENAPersistableObject;
import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import r01f.patterns.ThrowingFunction;
import r01f.securitycontext.SecurityContext;
import r01f.service.ServiceHandler;
import r01f.types.TimeLapse;
import r01f.util.types.collections.CollectionUtils;

/**
 * A cache for config objects
 * It contains TWO levels of cache:
 * 		[1] - A cache for the list of config objects oids (e.g. the list of admins oids)
 * 		[2] - A cache for the config objects
 * 
 * The caches are refreshed periodically:
 * 		[1] - The list of config objects oids is refreshed periodically (e.g. every 5 minutes)
 * 		[2] - The config objects:
 * 					- EXPIRES time after being cached (so they disappear from the cache and are retrieved again when needed)
 * 					- Are REFRESHED time after being cached (so they are reloaded in the cache if they have been updated in the DB since they were cached)
 */
@Slf4j
public abstract class DN00DENAConfigObjectCacheBase<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
    	   implements ServiceHandler {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////	
	public static final TimeLapse OIDs_CACHE_REFRESH_INTERVAL = TimeLapse.createFor("5min");
	public static final TimeLapse CFG_CACHE_ITEM_EXPIRE_INTERVAL = TimeLapse.createFor("5min");
	public static final TimeLapse CFG_CACHE_ITEM_REFRESH_INTERVAL = TimeLapse.createFor("5min");
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////	
	protected final Class<M> _modelObjectType;
	/**
	 * A cached list of all the config objects registered in the system. 
	 */
	protected final AtomicReference<Collection<O>> _oidsCache = new AtomicReference<>(List.of());	// thread-safe cache 
																									// AtomicReference is a good solution when the collection is threaten as a whole, 
																									// e.g. when refreshing the whole list of oids at once and not item by item)
																									// if the cache is updated item by item, use a concurrent collection (e.g. ConcurrentHashMap) instead
	protected Instant _oidsCacheLastRefreshAt;
	/**
	 * A cache of the config objs (e.g. the admin's host) 
	 */
	protected final LoadingCache<O,DN01DENAPersistableCacheEntry> _cfgCache;
	/**
	 * A scheduler for periodic tasks related to config cache.
	 */
	protected final ScheduledExecutorService _scheduler = Executors.newSingleThreadScheduledExecutor();
	
	protected final Provider<SecurityContext> _securityContextProvider;
	protected final DN00IsCRUDServicesForDENAPersistableObject<O,M> _crud;
	protected final DN00IsFindServicesForDENAPersistableObject<O,M> _find;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected <CRUD extends DN00IsCRUDServicesForDENAPersistableObject<O,M>,
			   FIND extends DN00IsFindServicesForDENAPersistableObject<O,M>> DN00DENAConfigObjectCacheBase(final Class<M> modelObjectType,
					   																					   // the system security context provider needed for the cache
																										   final Provider<SecurityContext> securityContextProvider,
																										   // the CRUD & FIND services
																										   final CRUD crud,final FIND find) {
		_modelObjectType = modelObjectType;
		_securityContextProvider = securityContextProvider;
		_crud = crud;
		_find = find;
		
		// create the cache
		// see: https://github.com/google/guava/wiki/CachesExplained
		RemovalListener<O,DN01DENAPersistableCacheEntry> cacheRemovalListener = null;
		cacheRemovalListener = notif -> log.warn("[{} cache] > remove config cached at {} because {}",
												 _modelObjectType.getSimpleName(),
				 								 notif.getValue().getCachedAt(),notif.getCause());
		_cfgCache = CacheBuilder.newBuilder()
								.maximumSize(100)							
								// expiration
								//		- expireAfterWrite: the cache entry is removed from the cache after the specified time has passed since it was cached (e.g. 5 minutes)
								//		- refreshAfterWrite: the cache entry is refreshed (reloaded) after the specified time has passed since it was cached (e.g. 5 minutes)
								.expireAfterWrite(CFG_CACHE_ITEM_EXPIRE_INTERVAL.asMinutes(),TimeUnit.MINUTES)		
								.removalListener(cacheRemovalListener)								
								.refreshAfterWrite(CFG_CACHE_ITEM_REFRESH_INTERVAL.asMinutes(),TimeUnit.MINUTES)
								// build the cache with a loader that loads the config for a given oid
								.build(new CacheLoader<O,DN01DENAPersistableCacheEntry>() {
												private final SecurityContext _securityContext = securityContextProvider.get();
												
												@Override
												public DN01DENAPersistableCacheEntry load(final O key) { // no checked exception
													return _loadAdminConfigBy(_securityContext,
																		 	  key);
												}
												@Override
												public ListenableFuture<DN01DENAPersistableCacheEntry> reload(final O key,
																							   		 	      final DN01DENAPersistableCacheEntry prevConfig) {
													// [1] - Get the time when the cached [config] record was last updated
													Instant lastUpdateDate = crud.getLastUpdateDate(_securityContext,
																									prevConfig.getCfgObj().getOid())
																				 .getOrNull();
													if (lastUpdateDate == null) {
														log.warn("[{} cache]: cannot get the last update date for config obj={}... maybe the record was deleted",
																 _modelObjectType.getSimpleName(),
																 key);
														return Futures.immediateFuture(prevConfig);
													}
													log.info("[{} cache]: reload / refresh config obj={} cache (stored time-stamp={} actual time-stamp={}",
															 _modelObjectType.getSimpleName(),
															 prevConfig.getCfgObj().getOid(),
															 prevConfig.getLastUpdatedAt(),lastUpdateDate);
													
													// [2] - Return a future that reloads the config
													if (prevConfig.needsReloadIfLastUpdatedAt(lastUpdateDate)) {
														ListenableFutureTask<DN01DENAPersistableCacheEntry> task = null; 
														task = ListenableFutureTask.create(() -> _loadAdminConfigBy(_securityContext,
																								  				    key));	// Callable													
														// load in a new thread > ASYNC
														// (the data will be available next time the config is required)
														_scheduler.execute(task);	// Executors.newSingleThreadExecutor()
																 					//			.execute(task);
														return task;
													} 
													// no need to refresh: return a "fake" future
													log.info("[{} cache]: cached config obj={} info does NOT needs to be reloaded",
															 _modelObjectType.getSimpleName(),
															 key);
													return Futures.immediateFuture(prevConfig);
												}
												private DN01DENAPersistableCacheEntry _loadAdminConfigBy(final SecurityContext securityContext,
																						   				 final O key) {
													log.info("[{} cache]: load config for key={} (an expensive operation)",
															 _modelObjectType.getSimpleName(),
															 key);
													
												    M cfgObj = _crud.load(securityContext,
																	  	  key)
												    				.getOrThrow();
													return new DN01DENAPersistableCacheEntry(cfgObj);	
												}
									   });
		
		
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ACCESSORS
/////////////////////////////////////////////////////////////////////////////////////////	
	protected Collection<O> _getPersistableObjectsOids() {
		Collection<O> cfgObjsOids = _oidsCache.get();
		// force refresh if the cache is empty (e.g. at startup)
		if (CollectionUtils.isNullOrEmpty(cfgObjsOids)) this.refreshOidsCache();	// BEWARE!! this is a SYNC call (it should ONLY be used at startup to load the initial cache)
		
		return _oidsCache.get();
	}
	protected M _getPersistableObjectConfig(final O cfgObjOid) {
		try {
			return _cfgCache.get(cfgObjOid)
							.getCfgObj();
		} catch (Exception ex) {
			log.error("[{} cache]: error loading config for oid={}: {}",
					  _modelObjectType.getSimpleName(),
					  cfgObjOid,
					  ex.getMessage(),ex);
			return null;
		}
	}
	public Collection<M> _getAllPersistableObjectsConfigs() {
		return _getPersistableObjectsOids()					// cached list of objs oids
				   .stream()
				   .map(ThrowingFunction.exceptionIgnoredAndReturnNull(this::_getPersistableObjectConfig))		// cached config for each oid
				   .filter(Objects::nonNull)	// filter out nulls (in case of error loading the config for an oid)
				   .collect(Collectors.toList());
	}
	public boolean exists(final O cfgObjOid) {
		if (_oidsCacheLastRefreshAt == null) this.refreshOidsCache();
		return _oidsCache.get()
						 .contains(cfgObjOid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ServiceHandler
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void start() {
		// schedule the periodic refresh of the persistable objects oids list cache
		 log.warn(" #### start {}", this.getClass().getName());
		_scheduler.scheduleAtFixedRate(this::refreshOidsCache, 
									   15000,	// initial delay
									   OIDs_CACHE_REFRESH_INTERVAL.asMillis(),TimeUnit.MILLISECONDS);
	}
	@Override
	public void stop() {
		 log.warn(" #### stop {}", this.getClass().getName());
		_scheduler.shutdown();
	}	
/////////////////////////////////////////////////////////////////////////////////////////
//	ADMIN LIST CACHE
/////////////////////////////////////////////////////////////////////////////////////////	
	public void refreshOidsCache() {
		log.warn("[{} cache]: refresh the config objs list",
				 _modelObjectType.getSimpleName());
		
        // [1] - Load the config objs oids
        Collection<O> oids = _find.findAll(_securityContextProvider.get())
								  .getOrThrow();
        // [2] - Wrap in unmodifiable list for safety and swap it in
        _oidsCache.set(Collections.unmodifiableCollection(oids));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ADMIN CFG CACHE ENTRY
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_") 
	private class DN01DENAPersistableCacheEntry
	   implements Serializable {
			
		private static final long serialVersionUID = -3077860130764155886L;
		
		@Getter private final M _cfgObj;	// TODO replace this for an object that contains the info needed to do the sync 
		@Getter private final Instant _cachedAt;
	
		DN01DENAPersistableCacheEntry(final M cfgObj) {
			_cfgObj = cfgObj;
			_cachedAt = Instant.now();
		}
		public Instant getLastUpdatedAt() {
			return _cfgObj.getLastUpdateDate();
		}
		public boolean needsReloadIfLastUpdatedAt(final Instant lastUpdatedAt) {
			if (lastUpdatedAt == null) return false;	// if the record does not have a last update date, we assume it does not need to be reloaded
			return lastUpdatedAt.isAfter(this.getLastUpdatedAt());
		}
	}
}
