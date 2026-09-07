	package dena.api.common.model.oids.interop;


import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public abstract class DN00InteropOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsInteropObjectOID
			 extends DN00IsDENAObjectOID {
	// just extend
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Message Correlation OID
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="messageCorrelationOID")
	public record DN00MessageCorrelationOID(@Getter String id)
	   implements DN00IsInteropObjectOID {
		
		public static DN00MessageCorrelationOID forId(final String id) {
			return new DN00MessageCorrelationOID(id);
		}
		public static DN00MessageCorrelationOID valueOf(final String str) {
			return new DN00MessageCorrelationOID(str);
		}
		public static DN00MessageCorrelationOID supply() {
			return DN00MessageCorrelationOID.forId(JavaOIDDispenser.generateGUID());
		}
		@Override
		public String toString() {
			return this.id;
		}
	}

}
