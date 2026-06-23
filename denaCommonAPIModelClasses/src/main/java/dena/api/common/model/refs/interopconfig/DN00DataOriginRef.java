package dena.api.common.model.refs.interopconfig;


import dena.api.common.model.oids.interopconfig.DN00InteropConfigIDs.DN00DataOriginID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00DataOriginOID;
import dena.api.common.model.refs.DN00DENAObjectWithIDRefBase;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Reference to a data origin entity.
 *
 * <h2>Creating with Builder:</h2>
 */
@MarshallType(as="dataOrignRef")
@Accessors(prefix="_")
public class DN00DataOriginRef
	 extends DN00DENAObjectWithIDRefBase<DN00DataOriginOID,DN00DataOriginID> {

	private static final long serialVersionUID = -5312844416460274355L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DataOriginRef() {
		super();
	}
	public DN00DataOriginRef(final DN00DataOriginOID oid,final DN00DataOriginID id) {
		super(oid,id);
	}
	public DN00DataOriginRef(final DN00DataOriginOID oid) {
		super(oid);
	}
	public DN00DataOriginRef(final DN00DataOriginID id) {
		super(id);
	}
	public static DN00DataOriginRef from(final DN00DataOriginOID oid,final DN00DataOriginID id) {
		return new DN00DataOriginRef(oid,id);
	}
	public static DN00DataOriginRef from(final DN00DataOriginOID oid) {
		return new DN00DataOriginRef(oid);
	}
	public static DN00DataOriginRef from(final DN00DataOriginID id) {
		return new DN00DataOriginRef(id);
	}
}
