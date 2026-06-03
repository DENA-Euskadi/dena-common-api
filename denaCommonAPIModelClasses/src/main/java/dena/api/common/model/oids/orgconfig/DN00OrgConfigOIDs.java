package dena.api.common.model.oids.orgconfig;

import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigObjectOID;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.guids.OID;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00OrgConfigOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsOrgConfigObjectOID
		     extends DN00IsDENAConfigObjectOID {
		// just extend
	}
	public interface DN00IsOrgConfigPersistableObjectOID
			 extends DN00IsDENAConfigPersistableObjectOID,
			 		 DN00IsOrgConfigObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	OrgAdmin / OrgAdminGroup OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="orgAdminOid")
	public record DN00OrgAdminGroupOID(String id)
	   implements DN00IsOrgConfigPersistableObjectOID {

        public static DN00OrgAdminGroupOID forId(final String id) {
            return new DN00OrgAdminGroupOID(id);
        }
        public static DN00OrgAdminGroupOID valueOf(final String str) {
            return new DN00OrgAdminGroupOID(str);
        }
        public static DN00OrgAdminGroupOID supply() {
     		return DN00OrgAdminGroupOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
	@MarshallType(as="orgAdminOid")
	public record DN00OrgAdminOID(String id)
	   implements DN00IsOrgConfigPersistableObjectOID {

        public static DN00OrgAdminOID forId(final String id) {
            return new DN00OrgAdminOID(id);
        }
        public static DN00OrgAdminOID valueOf(final String str) {
            return new DN00OrgAdminOID(str);
        }
        public static DN00OrgAdminOID supply() {
     		return DN00OrgAdminOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
        	return this.id;
        }
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsOrgSubjectOID
			 extends OID {
		// just a marker interface to be implemented by all OIDs of Org subjects
	}
}
