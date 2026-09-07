package dena.api.common.model.oids.security;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.security.DN00SecurityOIDs.DN00IsSecurityPersistableObjectOID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.enums.EnumWithCode;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00SecurityIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsSecurityObjectID
		     extends DN00IsDENAObjectID {
		// just extend
	}
	public interface DN00IsSecurityPersistableObjectID<O extends DN00IsSecurityPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsSecurityObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	@MarshallType(as="securityIdPId")
	public record DN00SecurityIDPID(String id)
	   implements DN00IsSecurityObjectID {

        public static DN00SecurityIDPID forId(final String id) {
            return new DN00SecurityIDPID(id);
        }
        public static DN00SecurityIDPID valueOf(final String str) {
            return new DN00SecurityIDPID(str);
        }
        @Override
		public String toString() {
        	return this.asString();
        }
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	OAUTH 
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="securityClientId")
	public record DN00SecurityClientID(String id)
	   implements DN00IsSecurityObjectID {

        public static DN00SecurityClientID forId(final String id) {
            return new DN00SecurityClientID(id);
        }
        public static DN00SecurityClientID valueOf(final String str) {
            return new DN00SecurityClientID(str);
        }
        @Override
		public String toString() {
        	return this.asString();
        }
	}
	@MarshallType(as="securityTokenId")
	public record DN00SecurityToken(String id)
	   implements DN00IsSecurityObjectID {

        public static DN00SecurityToken forId(final String id) {
            return new DN00SecurityToken(id);
        }
        public static DN00SecurityToken valueOf(final String str) {
            return new DN00SecurityToken(str);
        }
        @Override
		public String toString() {
        	return this.asString();
        }
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	CLIENT INSTALLMENT
/////////////////////////////////////////////////////////////////////////////////////////	
	@Accessors(prefix="_")
	@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
	public enum DN00ClientInstallmentStartupErrorCode
	 implements EnumWithCode<Integer,DN00ClientInstallmentStartupErrorCode> {
        CLIENT_INSTALLMENT_OID_NOT_FOUND(100),
        CLIENT_INSTALLMENT_COULD_NOT_BE_CREATED(101),
        CLIENT_INSTALLMENT_COULD_NOT_BE_UPDATED(102),
        
        CLIENT_APP_VERSION_NOT_FOUND(200),
        CLIENT_APP_VERSION_ERROR(201),
        
        PERSON_NOT_FOUND(300),
        PERSON_NOT_MATCHING(301),
     
        UNKNOWN_ERROR(-1);

		@Getter private final Class<Integer> _codeType = Integer.class;
		@Getter private final Integer _code;
	}
}
