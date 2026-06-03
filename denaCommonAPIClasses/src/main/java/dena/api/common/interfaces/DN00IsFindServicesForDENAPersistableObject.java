package dena.api.common.interfaces;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.FindServicesForModelObject;

public interface DN00IsFindServicesForDENAPersistableObject<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
		 extends FindServicesForModelObject<O,M>,
		 		 DN00IsDENAPersistenceServiceInterface {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Find all the objects but summarized (i.e. only the fields needed to build a summary are loaded)
	 * (just for testing purposes, in real life this method should be paginated > use SEARCH)
	 * @param securityContext
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<M> findAllSummarized(final SecurityContext securityContext,
													final Language lang);
}
