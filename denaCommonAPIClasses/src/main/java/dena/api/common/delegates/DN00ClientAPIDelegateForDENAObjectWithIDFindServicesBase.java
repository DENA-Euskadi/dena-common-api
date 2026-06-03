package dena.api.common.delegates;

import dena.api.common.interfaces.DN00IsFindServicesForDENAPersistableObjectWithID;
import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import jakarta.inject.Provider;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectFindServices;

public abstract class DN00ClientAPIDelegateForDENAObjectWithIDFindServicesBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>>
	     	  extends ClientAPIDelegateForModelObjectFindServices<O,M> {
	public DN00ClientAPIDelegateForDENAObjectWithIDFindServicesBase(final Provider<SecurityContext> securityContextProvider,
															  		final Marshaller modelObjectsMarshaller,
															  	    final DN00IsFindServicesForDENAPersistableObjectWithID<O,I,M> services) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  services);
	 }
}
