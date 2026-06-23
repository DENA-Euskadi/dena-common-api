package dena.api.common.delegates;

import dena.api.common.interfaces.DN00IsCRUDServicesForDENAPersistableObject;
import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import jakarta.inject.Provider;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;

public abstract class DN00ClientAPIDelegateForDENAObjectCRUDServicesBase<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
	     	  extends ClientAPIDelegateForModelObjectCRUDServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////	
	public DN00ClientAPIDelegateForDENAObjectCRUDServicesBase(final Provider<SecurityContext> securityContextProvider,
															  final Marshaller modelObjectsMarshaller,
															  final DN00IsCRUDServicesForDENAPersistableObject<O,M> services) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  services);
	 }
}
