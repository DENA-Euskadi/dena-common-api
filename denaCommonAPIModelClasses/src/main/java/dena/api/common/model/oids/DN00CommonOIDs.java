package dena.api.common.model.oids;

import lombok.NoArgsConstructor;
import r01f.guids.OID;
import r01f.guids.PersistableObjectOID;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00CommonOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsDENAObjectOID
		     extends OID {

		public String id();

		@Override
		public default String asString() {
			return this.id();
		}
	}
	public interface DN00IsDENAPersistableObjectOID
			 extends PersistableObjectOID,
			 		 DN00IsDENAObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the consent domain
	}
}
