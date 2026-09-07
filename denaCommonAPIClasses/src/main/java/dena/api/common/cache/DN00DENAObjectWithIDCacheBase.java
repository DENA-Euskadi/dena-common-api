package dena.api.common.cache;

import dena.api.common.interfaces.DN00IsCRUDServicesForDENAPersistablObjectWithID;
import dena.api.common.interfaces.DN00IsFindServicesForDENAPersistableObject;
import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import dena.api.common.model.refs.DN00IsDENAObjectWithIDRef;
import dena.api.common.model.refs.DN00IsDENAObjectWithIDRef.DN00DENAObjectWithIDRefFactory;
import jakarta.inject.Provider;
import r01f.securitycontext.SecurityContext;

public abstract class DN00DENAObjectWithIDCacheBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>> 
			  extends DN00DENAObjectCacheBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * A bidirectional map to cache the OID-ID associations for the objects in the cache.
	 */
	private final DN00DENAObjectRefSolver<O,I> _refSolver;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected <CRUD extends DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>,
			   FIND extends DN00IsFindServicesForDENAPersistableObject<O,M>> DN00DENAObjectWithIDCacheBase(final Class<M> modelObjectType,
			   																					   		   // the system security context provider needed for the cache
																								   		   final Provider<SecurityContext> securityContextProvider,
																								   		   // the CRUD & FIND services
																								   		   final CRUD crud,final FIND find) {
		super(modelObjectType,
			  securityContextProvider,
			  crud,find);
		_refSolver = new DN00DENAObjectRefSolver<>(// retrieve the ID of an object given its OID
												   oid -> _retrieveIddOf(oid),
												   // retrieve the OID of an object given its ID
												   id -> _retrieveOidOf(id));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	private I _retrieveIddOf(final O oid) {
		return _crud().getIdOf(_securityContextProvider.get(),
							   oid)
					  .getOrDefault(null);
	}
	private O _retrieveOidOf(final I id) {
		return _crud().getOidOf(_securityContextProvider.get(),
								id)
					  .getOrDefault(null);
	}
	@SuppressWarnings("unchecked")
	private DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M> _crud() {
		return (DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>)_crud;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	public I getIdOf(final O oid) {
		return _refSolver.getIdOf(oid);
	}
	public O getOidOf(final I id) {
		return _refSolver.getOidOf(id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	protected <R extends DN00IsDENAObjectWithIDRef<O,I>> R _completeRefIfNeeded(final R ref,
																				final DN00DENAObjectWithIDRefFactory<O,I,R> refFactory) {
		return _refSolver.completeRefIfNeeded(ref,
											  refFactory);
	}
}
