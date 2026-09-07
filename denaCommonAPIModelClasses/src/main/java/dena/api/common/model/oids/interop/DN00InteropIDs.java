package dena.api.common.model.oids.interop;


import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.enums.EnumExtended;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public abstract class DN00InteropIDs {
    
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
    public interface DN00IsInteropObjectID
			 extends DN00IsDENAObjectID {
    	// just extend
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	COMPONENT ID
/////////////////////////////////////////////////////////////////////////////////////////
    @MarshallType(as="interopComponentId")
	public enum DN00InteropComponent
	 implements EnumExtended<DN00InteropComponent> {
		CLIENT_INSTALLMENT,
		DENA_CORE,
		DENA_ADMIN_CONNECTOR,
		ADMIN;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////p	
	@MarshallType(as="responseStatusCode")
	public record DN00InteropResponseStatusCode(String id)
	   implements DN00IsInteropObjectID {
	
		public static DN00InteropResponseStatusCode forId(final String id) {
			return new DN00InteropResponseStatusCode(id);
		}
		public static DN00InteropResponseStatusCode valueOf(final String str) {
			return new DN00InteropResponseStatusCode(str);
		}
		public static DN00InteropResponseStatusCode named(final String str) {
			return new DN00InteropResponseStatusCode(str);
		}
		@Override
		public String toString() {
			return this.id;
		}
	}
}
