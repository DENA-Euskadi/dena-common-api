package dena.api.common.interfaces;

import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.services.interfaces.CRUDServicesForModelObjectWithID;

public interface DN00IsCRUDServicesForDENAPersistablObjectWithID<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>>
		 extends CRUDServicesForModelObjectWithID<O,I,M>,
		 		 DN00IsCRUDServicesForDENAPersistableObject<O,M>,
		 		 DN00IsDENAPersistenceServiceInterface {
	// just a marker interface to
}
