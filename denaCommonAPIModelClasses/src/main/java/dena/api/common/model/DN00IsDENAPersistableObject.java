package dena.api.common.model;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.model.PersistableModelObject;

public interface DN00IsDENAPersistableObject<O extends DN00IsDENAPersistableObjectOID>
		 extends PersistableModelObject<O>,
				 DN00IsDENAModelObject {
	// just a marker interface to be implemented by all persistable model objects of the consent domain
}
