package dena.api.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dena.api.common.interfaces.DN00IsCRUDServicesForDENAPersistablObjectWithID;
import dena.api.common.interfaces.DN00IsFindServicesForDENAPersistableObjectWithID;
import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import dena.api.common.model.refs.DN00IsDENAObjectWithIDRef;
import dena.api.common.model.refs.DN00IsDENAObjectWithIDRef.DN00DENAObjectWithIDRefFactory;
import jakarta.inject.Provider;
import r01f.securitycontext.SecurityContext;

public abstract class DN00DENAConfigObjectWithIDCacheBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>> 
			  extends DN00DENAConfigObjectCacheBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * A bidirectional map to cache the OID-ID associations for the objects in the cache.
	 */
	private final DN00BidirMapForOIDAndID _oidAndIdBidiMap = new DN00BidirMapForOIDAndID();
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected <CRUD extends DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>,
			   FIND extends DN00IsFindServicesForDENAPersistableObjectWithID<O,I,M>> DN00DENAConfigObjectWithIDCacheBase(final Class<M> modelObjectType,
					   																					   				 // the system security context provider needed for the cache
																										   				 final Provider<SecurityContext> securityContextProvider,
																										   				 // the CRUD & FIND services
																										   				 final CRUD crud,final FIND find) {
		super(modelObjectType,
			  securityContextProvider,
			  crud,find);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public I getIdOf(final O oid) {
		I outId = null;
		
		// [1] - Try the cache
		outId = _oidAndIdBidiMap.getIdOf(oid);
		if (outId != null) return outId;
		
		// [2] - The ID-OID association is not in the cache retrieve it 
		outId = ((DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>)_crud).getIdOf(_securityContextProvider.get(),
																						oid)
																			   .getOrDefault(null);
		if (outId != null) _oidAndIdBidiMap.put(oid,outId);	// cache the association for future use
		
		// [99] - Return the ID (if any)
		return outId;
	}
	@SuppressWarnings("unchecked")
	public O getOidOf(final I id) {
		O outOid = null;
		
		// [1] - Try the cache
		outOid = _oidAndIdBidiMap.getOidOf(id);
		if (outOid != null) return outOid;
		
		// [2] - The OID-ID association is not in the cache retrieve it
		outOid = ((DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>)_crud).getOidOf(_securityContextProvider.get(),
																						  id)
																			    .getOrDefault(null);
		if (outOid != null) _oidAndIdBidiMap.put(outOid,id);	// cache the association for future use	
		
		// [99] - Return the OID (if any)
		return outOid;
	}
	protected <R extends DN00IsDENAObjectWithIDRef<O,I>> R _completeRefIfNeeded(final R ref,
																				final DN00DENAObjectWithIDRefFactory<O,I,R> refFactory) {
		if (ref == null) return null;
		
		R outRef = null;
		if (ref.getOid() != null && ref.getId() != null) {
			// already complete ref
			outRef = ref;
		} else if (ref.getOid() != null) {
			// ref with OID but no ID: retrieve the ID and complete the ref
			I id = this.getIdOf(ref.getOid());
			outRef = refFactory.createRefFrom(ref.getOid(),id);
		} else if (ref.getId() != null) {
			// ref with ID but no OID: retrieve the OID and complete the ref
			O oid = this.getOidOf(ref.getId());
			outRef = refFactory.createRefFrom(oid,ref.getId());
		}
		return outRef;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	BidiMap between OID and ID (alternative to Guava's BiMap or Apache Commons BidiMap)
/////////////////////////////////////////////////////////////////////////////////////////	
	private class DN00BidirMapForOIDAndID {
		private final Map<O,I> _oidToId = new ConcurrentHashMap<>();
		private final Map<I,O> _idToOid = new ConcurrentHashMap<>();
		private final Lock _lock = new ReentrantLock();
		
		public void put(final O oid,final I id) {
			if (oid == null || id == null) throw new IllegalArgumentException("OID and ID cannot be null");
			
			// use a ReentrantLock that ensures that the put and remove operations are atomic.
			// AVOID an state where _oidToId has the data but idToOid doesn't yet
			_lock.lock();
	        try {
	            // a. If [id from oid] exists, remove the mapping from the [oid from id] map
	            I idFRomOid = _oidToId.get(oid);
	            if (idFRomOid != null) _idToOid.remove(idFRomOid);
	
	            // b. If [oid from id] exists, remove the mapping from the [id from oid] map
	            O oidFromId = _idToOid.get(id);
	            if (oidFromId != null) _oidToId.remove(oidFromId);
	
	            // c. Perform the actual insertion
	            _oidToId.put(oid,id);
	            _idToOid.put(id,oid);
	        } finally {
	            _lock.unlock();
	        }
		}
		public boolean removeByOid(final O oid) {
			_lock.lock();
	        try {
	            I idFromOid = _oidToId.remove(oid);
	            if (idFromOid != null) _idToOid.remove(idFromOid);
	            return idFromOid != null;
	        } finally {
	            _lock.unlock();
	        }
		}
		public boolean removeById(final I id) {
			_lock.lock();
	        try {
	            O oidFromId = _idToOid.remove(id);
	            if (oidFromId != null) _oidToId.remove(oidFromId);
	            return oidFromId != null;
	        } finally {
	            _lock.unlock();
	        }
		}
		public boolean containsOid(final O oid) {
			return _oidToId.containsKey(oid);
		}
		public boolean containsId(final I id) {
			return _idToOid.containsKey(id);
		}
		public I getIdOf(final O oid) {
			return _oidToId.get(oid);
		}
		public O getOidOf(final I id) {
			return _idToOid.get(id);
		}
		public int size() {
			return _oidToId.size();	// both maps should have the same size
		}
	}
}
