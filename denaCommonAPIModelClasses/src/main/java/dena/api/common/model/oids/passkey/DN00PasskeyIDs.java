package dena.api.common.model.oids.passkey;

import dena.api.common.model.config.DN00DENAConfigIDs.DN00IsDENAConfigObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.passkey.DN00PasskeyOIDs.DN00IsPasskeyPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00PasskeyIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsPasskeyObjectID
		     extends DN00IsDENAConfigObjectID {
		// just extend
	}
	public interface DN00IsPasskeyPersistableObjectID<O extends DN00IsPasskeyPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsPasskeyObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Passkey domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Passkey IDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="credentialId")
	public record DN00CredentialID(String id)
	   implements DN00IsPasskeyObjectID {

        public static DN00CredentialID forId(final String id) {
            return new DN00CredentialID(id);
        }
        public static DN00CredentialID valueOf(final String str) {
            return new DN00CredentialID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
	
	@MarshallType(as="publicKeyId")
	public record DN00PublicKeyID(String id)
	   implements DN00IsPasskeyObjectID {

        public static DN00PublicKeyID forId(final String id) {
            return new DN00PublicKeyID(id);
        }
        public static DN00PublicKeyID valueOf(final String str) {
            return new DN00PublicKeyID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
	
	@MarshallType(as="aaguidId")
	public record DN00AaguidID(String id)
	   implements DN00IsPasskeyObjectID {

        public static DN00AaguidID forId(final String id) {
            return new DN00AaguidID(id);
        }
        public static DN00AaguidID valueOf(final String str) {
            return new DN00AaguidID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
	
	@MarshallType(as="userHandleId")
	public record DN00UserHandleID(String id)
	   implements DN00IsPasskeyObjectID {

        public static DN00UserHandleID forId(final String id) {
            return new DN00UserHandleID(id);
        }
        public static DN00UserHandleID valueOf(final String str) {
            return new DN00UserHandleID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
	
}
