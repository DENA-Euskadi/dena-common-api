package dena.api.common.model.oids.orgconfig;

import dena.api.common.model.config.DN00DENAConfigIDs.DN00IsDENAConfigObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00IsOrgConfigPersistableObjectOID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00OrgAdminGroupOID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00OrgAdminOID;
import lombok.NoArgsConstructor;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00OrgConfigIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsOrgConfigObjectID
		     extends DN00IsDENAConfigObjectID {
		// just extend
	}
	public interface DN00IsOrgConfigPersistableObjectID<O extends DN00IsOrgConfigPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsOrgConfigObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Org
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="orgGroupId")
	public record DN00OrgAdminGroupID(String id)
	   implements DN00IsOrgConfigPersistableObjectID<DN00OrgAdminGroupOID> {

        public static DN00OrgAdminGroupID forId(final String id) {
            return new DN00OrgAdminGroupID(id);
        }
        public static DN00OrgAdminGroupID valueOf(final String str) {
            return new DN00OrgAdminGroupID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
	@MarshallType(as="orgId")
	public record DN00OrgAdminID(String id)
	   implements DN00IsOrgConfigPersistableObjectID<DN00OrgAdminOID> {

        public static DN00OrgAdminID forId(final String id) {
            return new DN00OrgAdminID(id);
        }
        public static DN00OrgAdminID valueOf(final String str) {
            return new DN00OrgAdminID(str);
        }
        @Override
		public String toString() {
        	return this.id;
        }
	}
	@MarshallType(as="dir3OrgId")
	public record DN00DIR3OrgID(String id)
	   implements DN00IsOrgConfigObjectID {

        public static DN00DIR3OrgID forId(final String id) {
            return new DN00DIR3OrgID(id);
        }
        public static DN00DIR3OrgID valueOf(final String str) {
            return new DN00DIR3OrgID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
}
