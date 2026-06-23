package dena.api.common.delegates;

import com.google.common.reflect.TypeToken;

import dena.api.common.interfaces.DN00IsCRUDServicesForDENAPersistablObjectWithID;
import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import jakarta.inject.Provider;
import r01f.aspects.interfaces.dirtytrack.DirtyStateTrackable;
import r01f.generics.TypeRef;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceException;
import r01f.model.persistence.PersistenceOperationResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import r01f.services.api.delegates.ClientAPIModelObjectChangesTrack;

public abstract class DN00ClientAPIDelegateForDENAObjectWithIDCRUDServicesBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>>
	     	  extends ClientAPIDelegateForModelObjectCRUDServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////	
	public DN00ClientAPIDelegateForDENAObjectWithIDCRUDServicesBase(final Provider<SecurityContext> securityContextProvider,
															  		final Marshaller modelObjectsMarshaller,
															  	    final DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M> services) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  services);
	 }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads an object using it's id
	 * @param id
	 * @return
	 */
	public M loadById(final I id) {
		CRUDResult<M> result = this.getServiceProxyAs(new TypeToken<DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>>() { /* nothing */ })
				   				   .loadById(this.getSecurityContext(),
							 				 id);
		M outRecord = result.getOrThrow();
		if (outRecord instanceof DirtyStateTrackable) {
			ClientAPIModelObjectChangesTrack.startTrackingChangesOnLoaded(outRecord);
		}
		return outRecord;
	}
	/**
	 * Loads an object using it's id
	 * @param id
	 * @return
	 */
	public M loadByIdOrNull(final I id) {
		M outRecord = null;
		try {
			outRecord = this.loadById(id);
		} catch (PersistenceException persistEx) {
			if (!persistEx.isEntityNotFound()) throw persistEx;
		}
		return outRecord;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Checks if an object exists using it's id
	 * @param id
	 * @return
	 */
	public boolean exists(final I id) {  // TypeToken vs TypeREf [todo] checks.
		PersistenceOperationResult<Boolean> result = this.getServiceProxyAs(new TypeToken<DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>>() { /* nothing */ })
														 .exists(this.getSecurityContext(),
							 				 					 id);	
		return result.getOrThrow();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Gets the id of an object using it's oid
	 * @param oid
	 * @return
	 */
	public I getIdOf(final O oid) {
		PersistenceOperationResult<I> result = this.getServiceProxyAs(new TypeRef<DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>>() { /* nothing */ })
												   .getIdOf(this.getSecurityContext(),
														    oid);	
		return result.getOrThrow();
	}
	/**
	 * Gets the oid of an object using it's id
	 * @param oid
	 * @return
	 */
	public O getOidOf(final I id) {
		PersistenceOperationResult<O> result = this.getServiceProxyAs(new TypeRef<DN00IsCRUDServicesForDENAPersistablObjectWithID<O,I,M>>() { /* nothing */ })
												   .getOidOf(this.getSecurityContext(),
														     id);	
		return result.getOrThrow();
	}
}
