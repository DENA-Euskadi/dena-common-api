package dena.api.common.model.oids.audit;

import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigObjectOID;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;
import lombok.Getter;
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
	@MarshallType(as="auditEntryOid")
	public record DN00AuditEntryOID(@Getter String id)
	   implements DN00IsAuditRegistryPersistableObjectOID {

        public static DN00AuditEntryOID forId(final String id) {
            return new DN00AuditEntryOID(id);
        }
        public static DN00AuditEntryOID valueOf(final String str) {
            return new DN00AuditEntryOID(str);
        }
        public static DN00AuditEntryOID supply() {
     		return DN00AuditEntryOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
}
