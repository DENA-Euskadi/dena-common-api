package dena.api.common.model.refs;

import dena.api.common.model.DN00IsDENAModelObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.facets.HasOID;
import r01f.types.CanBeRepresentedAsString;
import r01f.util.types.Objects;

public interface DN00IsDENAObjectRef<O extends DN00IsDENAPersistableObjectOID>
		 extends HasOID<O>,
		 		 CanBeRepresentedAsString,
		 		 DN00IsDENAModelObject {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public default boolean is(final DN00IsDENAObjectRef<O> other) {
		if (this == other) return true;
		if (other == null) return false;
		if (this.getClass() != other.getClass()) return false;
		return Objects.areEqual(this.getOid(),other.getOid(),
								(oid1,oid2) -> oid1.is(oid2));
	}
	public default boolean isNOT(final DN00IsDENAObjectRef<O> other) {
		return !this.is(other);
	}
	public default boolean matchesOid(final O oid) {
		return this.getOid() != null 
			&& this.getOid().is(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	FACTORY	
/////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * A factory to create refs to DENA objects from their OID
	 * @param <O>
	 * @param <R>
	 */
	public interface DN00DENAObjectRefFactory<O extends DN00IsDENAPersistableObjectOID,R extends DN00IsDENAObjectRef<O>> {
		public R createRefFrom(final O oid);
	}
}
