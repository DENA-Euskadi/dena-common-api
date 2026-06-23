package dena.api.common.interfaces;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.services.interfaces.CountServicesForModelObject;

public interface DN00IsCountServicesForDENAPersistableObject<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
		 extends CountServicesForModelObject<O,M>, 
				 DN00IsDENAPersistenceServiceInterface {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
