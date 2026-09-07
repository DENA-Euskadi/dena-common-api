package dena.api.common.model.oids.person;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00PersonOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsPersonObjectOID
		     extends DN00IsDENAObjectOID {
		// just extend
	}
	public interface DN00IsPersonPersistableObjectOID
			 extends DN00IsDENAPersistableObjectOID,
			 		 DN00IsPersonObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Org domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Person OID
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="personOid")
	public record DN00PersonOID(@Getter String id)
	   implements DN00IsPersonPersistableObjectOID {

        public static DN00PersonOID forId(final String id) {
            return new DN00PersonOID(id);
        }
        public static DN00PersonOID valueOf(final String str) {
            return new DN00PersonOID(str);
        }
        public static DN00PersonOID supply() {
     		return DN00PersonOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
        	return this.asString();
        }
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	@MarshallType(as="personIdChangeOid")
	public record DN00PersonIDChangeOID(@Getter String id) 
	   implements DN00IsPersonPersistableObjectOID {

        public static DN00PersonIDChangeOID forId(final String id) {
            return new DN00PersonIDChangeOID(id);
        }
        public static DN00PersonIDChangeOID valueOf(final String str) {
            return new DN00PersonIDChangeOID(str);
        }
        public static DN00PersonIDChangeOID supply() {
     		return DN00PersonIDChangeOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
        	return this.asString();
        }
	}
}
