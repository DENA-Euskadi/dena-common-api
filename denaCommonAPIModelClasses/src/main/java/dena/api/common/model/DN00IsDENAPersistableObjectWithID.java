package dena.api.common.model;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.facets.HasID;

public interface DN00IsDENAPersistableObjectWithID<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>>
		 extends HasID<I>,
		 		 DN00IsDENAPersistableObject<O> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public I getId();
	@Override
	public void setId(I id);
}
