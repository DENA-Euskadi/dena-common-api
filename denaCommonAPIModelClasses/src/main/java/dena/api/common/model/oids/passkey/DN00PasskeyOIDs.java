package dena.api.common.model.oids.passkey;

import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigObjectOID;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00PasskeyOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsPasskeyObjectOID
		     extends DN00IsDENAConfigObjectOID {
		// just extend
	}
	public interface DN00IsPasskeyPersistableObjectOID
			 extends DN00IsDENAConfigPersistableObjectOID,
			 		 DN00IsPasskeyObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Passkey domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Passkey OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="authenticatorOid")
	public record DN00AuthenticatorOID(@Getter String id)
	   implements DN00IsPasskeyPersistableObjectOID {

        public static DN00AuthenticatorOID forId(final String id) {
            return new DN00AuthenticatorOID(id);
        }
        public static DN00AuthenticatorOID valueOf(final String str) {
            return new DN00AuthenticatorOID(str);
        }
        public static DN00AuthenticatorOID supply() {
     		return DN00AuthenticatorOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
	@MarshallType(as="personPasskeyOid")
	public record DN00PersonPasskeyOID(@Getter String id)
	   implements DN00IsPasskeyPersistableObjectOID {

        public static DN00PersonPasskeyOID forId(final String id) {
            return new DN00PersonPasskeyOID(id);
        }
        public static DN00PersonPasskeyOID valueOf(final String str) {
            return new DN00PersonPasskeyOID(str);
        }
        public static DN00PersonPasskeyOID supply() {
     		return DN00PersonPasskeyOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
        	return this.id;
        }
	}
	@MarshallType(as="passkeyLoginSessionOid")
	public record DN00PasskeyLoginSessionOID(@Getter String id)
	   implements DN00IsPasskeyPersistableObjectOID {

        public static DN00PasskeyLoginSessionOID forId(final String id) {
            return new DN00PasskeyLoginSessionOID(id);
        }
        public static DN00PasskeyLoginSessionOID valueOf(final String str) {
            return new DN00PasskeyLoginSessionOID(str);
        }
        public static DN00PasskeyLoginSessionOID supply() {
     		return DN00PasskeyLoginSessionOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
        	return this.id;
        }
	}
}
