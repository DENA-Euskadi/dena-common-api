package dena.api.common.model.oids.security;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
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
//	CLIENT INSTALLMENT OID
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="clientInstallmentOid")
	public record DN00ClientInstallmentOID(@Getter String id)
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
/////////////////////////////////////////////////////////////////////////////////////////
//	CLIENT APP
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="clientAppOid")
	public record DN00ClientAppOID(@Getter String id)
	   implements DN00IsSecurityPersistableObjectOID {

        public static DN00ClientAppOID forId(final String id) {
            return new DN00ClientAppOID(id);
        }
        public static DN00ClientAppOID valueOf(final String str) {
            return new DN00ClientAppOID(str);
        }
        public static DN00ClientAppOID supply() {
     		return DN00ClientAppOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
}
