package dena.api.common.model.oids.audit;

import dena.api.common.model.config.DN00DENAConfigIDs.DN00IsDENAConfigObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.audit.DN00AuditRegistryOIDs.DN00IsAuditRegistryPersistableObjectOID;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00AuditRegistryIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsAuditRegistryObjectID
		     extends DN00IsDENAConfigObjectID {
		// just extend
	}
	public interface DN00IsAuditRegistryPersistableObjectID<O extends DN00IsAuditRegistryPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsAuditRegistryObjectID {
		// just a marker interface to be implemented by all IDs of persistable model objects of the Audit domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Audit Registry IDs
/////////////////////////////////////////////////////////////////////////////////////////
//	@MarshallType(as="auditEventId")
//	public record DN00AuditEventID(String id)
//	   implements DN00IsAuditRegistryObjectID {
//
//        public static DN00AuditEventID from(final DN00InteropMessageType messageType) {
//			return DN00AuditEventID.forId(messageType.name());
//		}
//		public static DN00AuditEventID forId(final String id) {
//            return new DN00AuditEventID(id);
//        }
//        public static DN00AuditEventID valueOf(final String str) {
//            return new DN00AuditEventID(str);
//        }
//        public static DN00AuditEventID fromString(final String str) {
//			return new DN00AuditEventID(str);
//		}
//        @Override
//		public String toString() {
//			return this.id;
//		}
//	}
}
