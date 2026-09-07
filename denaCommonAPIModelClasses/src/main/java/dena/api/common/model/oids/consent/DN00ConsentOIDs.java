package dena.api.common.model.oids.consent;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00ConsentOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsConsentObjectOID
		     extends DN00IsDENAObjectOID {
		// just extend
	}
	public interface DN00IsConsentPersistableObjectOID
			 extends DN00IsDENAPersistableObjectOID,
			 		 DN00IsConsentObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the consent domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSENT OID
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="consentOid")
	public record DN00ConsentOID(@Getter String id)
	   implements DN00IsConsentPersistableObjectOID {

        public static DN00ConsentOID forId(final String id) {
            return new DN00ConsentOID(id);
        }
        public static DN00ConsentOID valueOf(final String str) {
            return new DN00ConsentOID(str);
        }
        public static DN00ConsentOID supply() {
     		return DN00ConsentOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
}
