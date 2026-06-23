package dena.api.common.model.refs;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.facets.HasID;

public interface DN00IsDENAObjectWithIDRef<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>> 
		 extends DN00IsDENAObjectRef<O>,
		 		 HasID<I> {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	public default boolean is(final I other) {
		return this.getId() != null && this.getId().is(other);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public default boolean containsBothOidAndId() {
		return this.getOid() != null 
			&& this.getId() != null;
	}
	public default boolean containsAnyOidOrId() {
		return this.getOid() != null 
			|| this.getId() != null;
	}
	public default boolean containsNeitherOidNorId() {
		return this.getOid() == null 
			&& this.getId() == null;
	}	
	public default boolean containsOid() {
		return this.getOid() != null; 		
	}
	public default boolean containsId() {
		return this.getId() != null; 		
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	FACTORY	
/////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * A factory to create refs to DENA objects from their OID
	 * @param <O>
	 * @param <I>
	 * @param <R>
	 */
	public interface DN00DENAObjectWithIDRefFactory<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,R extends DN00IsDENAObjectWithIDRef<O,I>> {
		public R createRefFrom(final O oid,final I id);
	}
}
