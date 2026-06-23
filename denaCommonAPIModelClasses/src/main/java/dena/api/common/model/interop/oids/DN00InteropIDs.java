package dena.api.common.model.interop.oids;


import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
	public record DN00InteropComponentID(String id)
	   implements DN00IsInteropObjectID {
		
		public static DN00InteropComponentID forId(final String id) {
			return new DN00InteropComponentID(id);
		}
		public static DN00InteropComponentID valueOf(final String str) {
			return new DN00InteropComponentID(str);
		}
		public static DN00InteropComponentID named(final String str) {
			return new DN00InteropComponentID(str);
		}
		@Override
		public String toString() {
			return this.id;
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORIGIN AND DESTINATION PARTY IDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="originPartyId")
	public record DN00InteropOriginPartyID(String id)
	   implements DN00IsInteropObjectID {
		
		public static DN00InteropOriginPartyID forId(final String id) {
			return new DN00InteropOriginPartyID(id);
		}
		public static DN00InteropOriginPartyID valueOf(final String str) {
			return new DN00InteropOriginPartyID(str);
		}
		public static DN00InteropOriginPartyID named(final String str) {
			return new DN00InteropOriginPartyID(str);
		}
		@Override
		public String toString() {
			return this.id;
		}
	}
	@MarshallType(as="destinationPartyId")
	public record DN00InteropDestinationPartyID(String id)
	   implements DN00IsInteropObjectID {
	
		public static DN00InteropDestinationPartyID forId(final String id) {
			return new DN00InteropDestinationPartyID(id);
		}
		public static DN00InteropDestinationPartyID valueOf(final String str) {
			return new DN00InteropDestinationPartyID(str);
		}
		public static DN00InteropDestinationPartyID named(final String str) {
			return new DN00InteropDestinationPartyID(str);
		}
		@Override
		public String toString() {
			return this.id;
		}
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
