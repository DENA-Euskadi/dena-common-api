package dena.api.common.model.oids;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.guids.OID;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00CommonIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsDENAObjectID
		     extends OID {

		public String id();

		@Override
		public default String asString() {
			return this.id();
		}
	}
	public interface DN00IsDENAPersistableObjectID<O extends DN00IsDENAPersistableObjectOID>
		     extends DN00IsDENAObjectID {
		/* a marker interface */
	}
}
