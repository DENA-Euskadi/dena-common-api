package dena.api.common.delegates;

import dena.api.common.interfaces.DN00IsCountServicesForDENAPersistableObject;
import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import jakarta.inject.Provider;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectCountServices;

public abstract class DN00ClientAPIDelegateForDENAObjectCountServicesBase<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
	     	  extends ClientAPIDelegateForModelObjectCountServices<O,M> {
	public DN00ClientAPIDelegateForDENAObjectCountServicesBase(final Provider<SecurityContext> securityContextProvider,
															   final Marshaller modelObjectsMarshaller,
															   final DN00IsCountServicesForDENAPersistableObject<O,M> services) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  services);
	 }
}
