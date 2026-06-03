package dena.api.common.model.oids.security;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00SecurityOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsSecurityObjectOID
		     extends DN00IsDENAObjectOID {
		// just extend
	}
	public interface DN00IsSecurityPersistableObjectOID
			 extends DN00IsDENAPersistableObjectOID,
			 		 DN00IsSecurityObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="clientInstallmentOid")
	public record DN00ClientInstallmentOID(String id)
	   implements DN00IsSecurityPersistableObjectOID {

        public static DN00ClientInstallmentOID forId(final String id) {
            return new DN00ClientInstallmentOID(id);
        }
        public static DN00ClientInstallmentOID valueOf(final String str) {
            return new DN00ClientInstallmentOID(str);
        }
        public static DN00ClientInstallmentOID supply() {
     		return DN00ClientInstallmentOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
}
