package dena.api.common.model.config;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00DENAConfigOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsDENAConfigObjectOID
		     extends DN00IsDENAObjectOID {
		// just extend
	}
	public interface DN00IsDENAConfigPersistableObjectOID
			 extends DN00IsDENAPersistableObjectOID,
			 		 DN00IsDENAConfigObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsDENAInteropConfigObjectOID
		     extends DN00IsDENAConfigObjectOID {
		// just extend
	}
	public interface DN00IsDENAInteropConfigPersistableObjectOID
			 extends DN00IsDENAConfigPersistableObjectOID,
			 		 DN00IsDENAInteropConfigObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
}
