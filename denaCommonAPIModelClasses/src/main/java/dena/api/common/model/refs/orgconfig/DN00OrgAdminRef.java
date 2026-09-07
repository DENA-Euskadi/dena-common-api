package dena.api.common.model.refs.orgconfig;

import dena.api.common.model.oids.DN00CommonObjectsValidators;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigIDs.DN00DIR3OrgID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigIDs.DN00OrgAdminID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00OrgAdminOID;
import dena.api.common.model.refs.DN00DENAObjectWithIDRefBase;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

/**
 * Reference to an organization admin entity.
 * <p>Contains the OID and additional IDs (DENA and DIR3) for identifying an org admin.</p>
 */
@Accessors(prefix="_")
public class DN00OrgAdminRef
	 extends DN00DENAObjectWithIDRefBase<DN00OrgAdminOID,DN00OrgAdminID> 
  implements SelfValidates<DN00OrgAdminRef> {

	 private static final long serialVersionUID = 7066584416460274355L;
/////////////////////////////////////////////////////////////////////////////////////////
//	OTHER IDs
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="dir3Id",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private DN00DIR3OrgID _dir3Id;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00OrgAdminRef() {
		super();
	}
	public DN00OrgAdminRef(final DN00OrgAdminRef other) {
		super(other);
		_dir3Id = other.getDir3Id();
	}
	public DN00OrgAdminRef(final DN00OrgAdminOID oid,final DN00OrgAdminID id,
						   final DN00DIR3OrgID dir3Id) {
		super(oid,id);
		_dir3Id = dir3Id;
	}
	public DN00OrgAdminRef(final DN00OrgAdminOID oid,final DN00OrgAdminID id) {
		this(oid,id,
			 null);	// no dir 3
	}
	public DN00OrgAdminRef(final DN00OrgAdminOID oid) {
		super(oid);
	}
	public DN00OrgAdminRef(final DN00OrgAdminID id) {
		super(id);
	}
	public DN00OrgAdminRef(final DN00DIR3OrgID dir3Id) {
		_dir3Id = dir3Id;
	}
	public static DN00OrgAdminRef from(final DN00OrgAdminOID oid,final DN00OrgAdminID id,final DN00DIR3OrgID dir3Id) {
		return new DN00OrgAdminRef(oid,id,dir3Id);
	}
	public static DN00OrgAdminRef from(final DN00OrgAdminOID oid,final DN00OrgAdminID id) {
		return new DN00OrgAdminRef(oid,id,
								   null);	// no dir3
	}
	public static DN00OrgAdminRef from(final DN00OrgAdminOID oid) {
		return new DN00OrgAdminRef(oid);
	}
	public static DN00OrgAdminRef from(final DN00OrgAdminID id) {
		return new DN00OrgAdminRef(id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public ObjectValidationResult<DN00OrgAdminRef> validate() {
		return DN00CommonObjectsValidators.<DN00OrgAdminRef>createObjWithIdRefValidator()
										  .validate(this);
	}
}
