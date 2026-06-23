package dena.api.common.model.refs.orgconfig;

import dena.api.common.model.oids.DN00CommonObjectsValidators;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigIDs.DN00OrgAdminGroupID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00OrgAdminGroupOID;
import dena.api.common.model.refs.DN00DENAObjectWithIDRefBase;
import lombok.experimental.Accessors;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

/**
 * Reference to an organization admin group entity.
 * <p>Contains the OID and additional ID (DENA) for identifying an org admin group.</p>
 */
@Accessors(prefix="_")
public class DN00OrgAdminGroupRef
	 extends DN00DENAObjectWithIDRefBase<DN00OrgAdminGroupOID,DN00OrgAdminGroupID>
  implements SelfValidates<DN00OrgAdminGroupRef> {

	private static final long serialVersionUID = 2847163950372618945L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00OrgAdminGroupRef() {
		super();
	}
	public DN00OrgAdminGroupRef(final DN00OrgAdminGroupOID oid,final DN00OrgAdminGroupID id) {
		super(oid,id);
	}
	public DN00OrgAdminGroupRef(final DN00OrgAdminGroupOID oid) {
		super(oid);
	}
	public DN00OrgAdminGroupRef(final DN00OrgAdminGroupID id) {
		super(id);
	}
	public static DN00OrgAdminGroupRef from(final DN00OrgAdminGroupOID oid,final DN00OrgAdminGroupID id) {
		return new DN00OrgAdminGroupRef(oid,id);
	}
	public static DN00OrgAdminGroupRef from(final DN00OrgAdminGroupOID oid) {
		return new DN00OrgAdminGroupRef(oid);
	}
	public static DN00OrgAdminGroupRef from(final DN00OrgAdminGroupID id) {
		return new DN00OrgAdminGroupRef(id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public ObjectValidationResult<DN00OrgAdminGroupRef> validate() {
		return DN00CommonObjectsValidators.<DN00OrgAdminGroupRef>createObjWithIdRefValidator()
									   	  .validate(this);
	}
}
