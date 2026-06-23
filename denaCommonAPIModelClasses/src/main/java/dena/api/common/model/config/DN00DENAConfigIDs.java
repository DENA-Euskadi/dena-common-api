package dena.api.common.model.config;

import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAInteropConfigPersistableObjectOID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00DENAConfigIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsDENAConfigObjectID
		     extends DN00IsDENAObjectID {
		// just extend
	}
	public interface DN00IsDENAConfigPersistableObjectID<O extends DN00IsDENAConfigPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsDENAConfigObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsDENAInteropConfigObjectID
		     extends DN00IsDENAConfigObjectID {
		// just extend
	}
	public interface DN00IsDENAInteropConfigPersistableObjectID<O extends DN00IsDENAInteropConfigPersistableObjectOID>
			 extends DN00IsDENAConfigPersistableObjectID<O>,
			 		 DN00IsDENAInteropConfigObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
}
