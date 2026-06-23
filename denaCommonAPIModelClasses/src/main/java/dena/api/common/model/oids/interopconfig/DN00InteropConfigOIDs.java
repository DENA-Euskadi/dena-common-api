package dena.api.common.model.oids.interopconfig;

import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigObjectOID;
import dena.api.common.model.config.DN00DENAConfigOIDs.DN00IsDENAConfigPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00InteropConfigOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsInteropConfigObjectOID
		     extends DN00IsDENAConfigObjectOID {
		// just extend
	}
	public interface DN00IsInteropConfigPersistableObjectOID
			 extends DN00IsDENAConfigPersistableObjectOID,
			 		 DN00IsInteropConfigObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the DataType domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DataType OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="dataTypeOid")
	public record DN00DataTypeOID(String id)
	   implements DN00IsInteropConfigPersistableObjectOID {

        public static DN00DataTypeOID forId(final String id) {
            return new DN00DataTypeOID(id);
        }
        public static DN00DataTypeOID valueOf(final String str) {
            return new DN00DataTypeOID(str);
        }
        public static DN00DataTypeOID supply() {
     		return DN00DataTypeOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DataOrigin OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="dataOriginOid")
	public record DN00DataOriginOID(String id)
	   implements DN00IsInteropConfigPersistableObjectOID {

        public static DN00DataOriginOID forId(final String id) {
            return new DN00DataOriginOID(id);
        }
        public static DN00DataOriginOID valueOf(final String str) {
            return new DN00DataOriginOID(str);
        }
        public static DN00DataOriginOID supply() {
     		return DN00DataOriginOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
        	return this.id;
        }
	}
}
