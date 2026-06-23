package dena.api.common.model.refs.interopconfig;


import dena.api.common.model.oids.interopconfig.DN00InteropConfigIDs.DN00DataTypeID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00DataTypeOID;
import dena.api.common.model.refs.DN00DENAObjectWithIDRefBase;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Reference to a [data type] entity.
 */
@MarshallType(as="dataTypeRef")
@Accessors(prefix="_")
public class DN00DataTypeRef
	 extends DN00DENAObjectWithIDRefBase<DN00DataTypeOID,DN00DataTypeID> {

	private static final long serialVersionUID = -5312844416460274355L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DataTypeRef() {
		super();
	}
	public DN00DataTypeRef(final DN00DataTypeOID oid,final DN00DataTypeID id) {
		super(oid,id);
	}
	public DN00DataTypeRef(final DN00DataTypeOID oid) {
		super(oid);
	}
	public DN00DataTypeRef(final DN00DataTypeID id) {
		super(id);
	}
	public static DN00DataTypeRef from(final DN00DataTypeOID oid,final DN00DataTypeID id) {
		return new DN00DataTypeRef(oid,id);
	}
	public static DN00DataTypeRef from(final DN00DataTypeOID oid) {
		return new DN00DataTypeRef(oid);
	}
	public static DN00DataTypeRef from(final DN00DataTypeID id) {
		return new DN00DataTypeRef(id);
	}
}
