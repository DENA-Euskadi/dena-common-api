package dena.api.common.interfaces;

import dena.api.common.model.DN00IsDENAPersistableObjectWithID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;

public interface DN00IsFindServicesForDENAPersistableObjectWithID<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,M extends DN00IsDENAPersistableObjectWithID<O,I>>
		 extends DN00IsFindServicesForDENAPersistableObject<O,M>,
		 		 DN00IsDENAPersistenceServiceInterface {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
