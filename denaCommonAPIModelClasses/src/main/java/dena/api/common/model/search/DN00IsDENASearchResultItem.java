package dena.api.common.model.search;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.facets.HasOID;
import r01f.model.IndexableModelObject;
import r01f.model.search.SearchResultItemForModelObject;

public interface DN00IsDENASearchResultItem<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O> & IndexableModelObject>
		 extends SearchResultItemForModelObject<M>,
		 		 HasOID<O> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

}
