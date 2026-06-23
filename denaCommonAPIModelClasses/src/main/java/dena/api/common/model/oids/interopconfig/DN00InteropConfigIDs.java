package dena.api.common.model.oids.interopconfig;

import dena.api.common.model.config.DN00DENAConfigIDs.DN00IsDENAConfigObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00DataOriginOID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00DataTypeOID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00IsInteropConfigPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00InteropConfigIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsInteropConfigObjectID
		     extends DN00IsDENAConfigObjectID {
		// just extend
	}
	public interface DN00IsInteropConfigPersistableObjectID<O extends DN00IsInteropConfigPersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			 		 DN00IsInteropConfigObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the Interop domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="dataTypeId")
	public record DN00DataTypeID(String id)
	   implements DN00IsInteropConfigPersistableObjectID<DN00DataTypeOID> {

        public static DN00DataTypeID forId(final String id) {
            return new DN00DataTypeID(id);
        }
        public static DN00DataTypeID valueOf(final String str) {
            return new DN00DataTypeID(str);
        }
        @Override
		public String toString() {
			return this.id;
		}
	}
	@MarshallType(as="dataOriginId")
	public record DN00DataOriginID(String id)
	   implements DN00IsInteropConfigPersistableObjectID<DN00DataOriginOID> {

        public static DN00DataOriginID forId(final String id) {
            return new DN00DataOriginID(id);
        }
        public static DN00DataOriginID valueOf(final String str) {
            return new DN00DataOriginID(str);
        }
        @Override
		public String toString() {
        	return this.id;
        }
	}
}
