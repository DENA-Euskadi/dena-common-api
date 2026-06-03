package dena.api.common.delegates;

import dena.api.common.interfaces.DN00IsSearchServicesForDENAPersistableObject;
import dena.api.common.model.search.DN00IsDENASearchFilter;
import dena.api.common.model.search.DN00IsDENASearchResultItem;
import jakarta.inject.Provider;
import r01f.guids.OID;
import r01f.model.IndexableModelObject;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectSearchServices;

public abstract class DN00ClientAPIDelegateForDENAObjectSearchServicesBase<F extends DN00IsDENASearchFilter,I extends DN00IsDENASearchResultItem<? extends OID,? extends IndexableModelObject>>
	     	  extends ClientAPIDelegateForModelObjectSearchServices<F,I> {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public DN00ClientAPIDelegateForDENAObjectSearchServicesBase(final Provider<SecurityContext> securityContextProvider,
															    final Marshaller modelObjectsMarshaller,
															    final DN00IsSearchServicesForDENAPersistableObject<F,I> services) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  services);
	 }
}
