package dena.api.common.model.securitycontext.user;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.person.DN00PersonOIDs.DN00IsPersonPersistableObjectOID;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00DENAPersonUserIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsPersonObjectID
		     extends DN00IsDENAObjectID {
		// just extend
	}
	public interface DN00IsPersonPersistableObjectID<O extends DN00IsPersonPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsPersonObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Person domain
	}
}
