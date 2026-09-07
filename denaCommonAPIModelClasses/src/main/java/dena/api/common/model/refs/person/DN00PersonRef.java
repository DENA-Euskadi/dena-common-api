package dena.api.common.model.refs.person;

import dena.api.common.model.oids.DN00CommonObjectsValidators;
import dena.api.common.model.oids.person.DN00PersonID;
import dena.api.common.model.oids.person.DN00PersonOIDs.DN00PersonOID;
import dena.api.common.model.refs.DN00DENAObjectWithIDRefBase;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

/**
 * Reference to a person entity.
 * <p>Contains only the OID since {@link DN00Person} does NOT have a business ID.</p>
 *
 * <h2>Creating with Builder:</h2>
 * @see DN00BuildersForPersonObjectRef
 */
@MarshallType(as="personRef")
@Accessors(prefix="_")
public class DN00PersonRef
	 extends DN00DENAObjectWithIDRefBase<DN00PersonOID,DN00PersonID> 
  implements SelfValidates<DN00PersonRef> {

	private static final long serialVersionUID = -5312844416460274355L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00PersonRef() {
		super();
	}
	public DN00PersonRef(final DN00PersonRef other) {
		super(other);
	}
	public DN00PersonRef(final DN00PersonOID oid,final DN00PersonID id) {
		super(oid,id);
	}
	public DN00PersonRef(final DN00PersonOID oid) {
		super(oid);
	}
	public DN00PersonRef(final DN00PersonID id) {
		super(id);
	}
	public static DN00PersonRef from(final DN00PersonOID oid,final DN00PersonID id) {
		return new DN00PersonRef(oid,id);
	}
	public static DN00PersonRef from(final DN00PersonOID oid) {
		return new DN00PersonRef(oid,null);
	}
	public static DN00PersonRef from(final DN00PersonID id) {
		return new DN00PersonRef(null,id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public ObjectValidationResult<DN00PersonRef> validate() {
		return DN00CommonObjectsValidators.<DN00PersonRef>createObjWithIdRefValidator()
										  .validate(this);
	}
}
