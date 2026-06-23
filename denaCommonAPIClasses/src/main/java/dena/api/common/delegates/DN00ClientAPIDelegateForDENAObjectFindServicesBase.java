package dena.api.common.delegates;

import java.util.Collection;

import dena.api.common.interfaces.DN00IsFindServicesForDENAPersistableObject;
import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import jakarta.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.SummarizedModelObject;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIDelegateForModelObjectFindServices;

@Slf4j
public abstract class DN00ClientAPIDelegateForDENAObjectFindServicesBase<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
	     	  extends ClientAPIDelegateForModelObjectFindServices<O,M> {
	public DN00ClientAPIDelegateForDENAObjectFindServicesBase(final Provider<SecurityContext> securityContextProvider,
															  final Marshaller modelObjectsMarshaller,
															  final DN00IsFindServicesForDENAPersistableObject<O,M> services) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  services);
	 }
	
/////////////////////////////////////////////////////////////////////////////////////////
// EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Find all the objects but summarized (i.e. only the fields needed to build a summary are loaded)
	 * (just for testing purposes, in real life this method should be paginated > use SEARCH)
	 * @param securityContext
	 * @param lang
	 * @return
	 */
	public Collection<? extends SummarizedModelObject<M>> findAllSummarized( final Language lang ) {
		@SuppressWarnings("unchecked")
		FindSummariesResult<M> findResult = this.getServiceProxyAs(DN00IsFindServicesForDENAPersistableObject.class)
												.findAllSummarized(this.getSecurityContext(),
														           lang);

		log.warn(findResult.debugInfo().toString());

		Collection<? extends SummarizedModelObject<M>> result = findResult.getOrThrow();
		return result;
	}
		
}
