package dena.api.common.delegates;

import dena.api.common.interfaces.DN00IsCRUDServicesForDENAPersistablObjectWithID;
import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import dena.api.common.model.refs.DN00IsDENAObjectWithIDRef;
import jakarta.inject.Provider;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectWithIDCRUDServices;

public abstract class DN00ClientAPIDelegateForDENAObjectWithIDCRUDServicesBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>>
	     	  extends ClientAPIDelegateForModelObjectWithIDCRUDServices<O,I,M> {
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
	 * Load by reference
	 * @param ref
	 * @return
	 */
	public <R extends DN00IsDENAObjectWithIDRef<O,I>> M load(final R ref) {
		if (ref == null || ref.containsNeitherOidNorId()) throw new IllegalArgumentException("A ref with either an oid or an id is required");
		
		M outObj = null;
		if (ref.containsOid()) {
			outObj = this.load(ref.getOid());
		} else if (ref.containsId()) {
			outObj = this.loadById(ref.getId());
		}
		return outObj;
	}
	/**
	 * Deletes by ref
	 * @param ref
	 * @return
	 */
	public <R extends DN00IsDENAObjectWithIDRef<O,I>> M delete(final R ref) {
		if (ref == null || ref.containsNeitherOidNorId()) throw new IllegalArgumentException("A ref with either an oid or an id is required");
		
		M outDeleted = null;
		if (ref.containsOid()) {
			outDeleted = this.delete(ref.getOid());
		} else if (ref.containsId()) {
			outDeleted = this.deleteById(ref.getId());
		}
		return outDeleted;
	}
	/**
	 * Checks if the object exists by reference
	 * @param ref
	 * @return
	 */
	public <R extends DN00IsDENAObjectWithIDRef<O,I>> boolean exists(final R ref) {
		if (ref == null || ref.containsNeitherOidNorId()) throw new IllegalArgumentException("A ref with either an oid or an id is required");
		
		boolean outExists = false;
		if (ref.containsOid()) {
			outExists = this.exists(ref.getOid());
		} else if (ref.containsId()) {
			outExists = this.exists(ref.getId());
		}
		return outExists;
	}
}
