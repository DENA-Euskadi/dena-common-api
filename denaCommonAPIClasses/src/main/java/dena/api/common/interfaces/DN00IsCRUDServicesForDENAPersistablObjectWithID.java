package dena.api.common.interfaces;

import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceOperationResult;
import r01f.securitycontext.SecurityContext;

public interface DN00IsCRUDServicesForDENAPersistablObjectWithID<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>>
		 extends DN00IsCRUDServicesForDENAPersistableObject<O,M>,
		 		 DN00IsDENAPersistenceServiceInterface {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads an object using it's id
	 * @param userContext
	 * @param id
	 * @return
	 */
	public CRUDResult<M> loadById(final SecurityContext securityContext,
								  final I id);
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Checks if an object exists using it's id
	 * @param securityContext
	 * @param id
	 * @return
	 */
	public PersistenceOperationResult<Boolean> exists(final SecurityContext securityContext,
												 	  final I id);
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the id of an object using it's oid
	 * @param securityContext
	 * @param oid
	 * @return
	 */
	public PersistenceOperationResult<I> getIdOf(final SecurityContext securityContext,
												 final O oid);
	/**
	 * Gets the oid of an object using it's id
	 * @param securityContext
	 * @param oid
	 * @return
	 */
	public PersistenceOperationResult<O> getOidOf(final SecurityContext securityContext,
												  final I id);
}
