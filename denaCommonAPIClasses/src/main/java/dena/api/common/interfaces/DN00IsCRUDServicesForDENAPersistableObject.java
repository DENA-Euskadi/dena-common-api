package dena.api.common.interfaces;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.services.interfaces.CRUDServicesForModelObject;

public interface DN00IsCRUDServicesForDENAPersistableObject<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
		 extends CRUDServicesForModelObject<O,M>,
		 		 DN00IsDENAPersistenceServiceInterface {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
