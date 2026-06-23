package dena.api.common.interfaces;

import dena.api.common.model.search.DN00IsDENASearchFilter;
import dena.api.common.model.search.DN00IsDENASearchResultItem;
import r01f.guids.OID;
import r01f.model.IndexableModelObject;
import r01f.services.interfaces.SearchServicesForModelObject;

public interface DN00IsSearchServicesForDENAPersistableObject<F extends DN00IsDENASearchFilter,I extends DN00IsDENASearchResultItem<? extends OID,? extends IndexableModelObject>>
	     extends SearchServicesForModelObject<F,I>,
	     		 DN00IsDENAServiceInterface {
	// nothing here
}