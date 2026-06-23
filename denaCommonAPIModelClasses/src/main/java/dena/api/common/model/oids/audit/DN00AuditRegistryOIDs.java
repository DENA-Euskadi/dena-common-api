package dena.api.common.model.oids.audit;

import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigObjectOID;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00AuditRegistryOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsAuditRegistryObjectOID
		     extends DN00IsDENAConfigObjectOID {
		// just extend
	}
	public interface DN00IsAuditRegistryPersistableObjectOID
			 extends DN00IsDENAConfigPersistableObjectOID,
			 		 DN00IsAuditRegistryObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Audit domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Audit Registry OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="auditRegistryOid")
	public record DN00AuditRegistryOID(String id)
	   implements DN00IsAuditRegistryPersistableObjectOID {

        public static DN00AuditRegistryOID forId(final String id) {
            return new DN00AuditRegistryOID(id);
        }
        public static DN00AuditRegistryOID valueOf(final String str) {
            return new DN00AuditRegistryOID(str);
        }
        public static DN00AuditRegistryOID supply() {
     		return DN00AuditRegistryOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
}
