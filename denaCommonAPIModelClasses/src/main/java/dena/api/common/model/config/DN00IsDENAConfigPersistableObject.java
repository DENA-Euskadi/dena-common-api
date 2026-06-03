package dena.api.common.model.config;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;

public interface DN00IsDENAConfigPersistableObject<O extends DN00IsDENAConfigPersistableObjectOID>
		 extends DN00IsDENAPersistableObject<O>,
				 DN00IsDENAConfigModelObject {
	// just a marker interface to be implemented by all persistable model objects of the Org domain
}
