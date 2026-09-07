package dena.api.common.model.refs.interopconfig;

import dena.api.common.model.oids.interopconfig.DN00InteropConfigIDs.DN00DataOriginInstanceID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigIDs.DN00DataTypeID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00DataTypeOID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigIDs.DN00OrgAdminID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00OrgAdminOID;
import dena.api.common.model.refs.orgconfig.DN00OrgAdminRef;
import lombok.Getter;

/**
 * References needed to specify a [data origin] for a given [person] in a [cold start] scenario (i.e. when the [person] has no SRMD record yet)
 */
public record DN00DataOriginForPersonColdStart(@Getter DN00OrgAdminRef adminRef,
											   @Getter DN00DataTypeRef dataTypeRef,@Getter DN00DataOriginInstanceID dataOriginInstanceId) {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DataOriginForPersonColdStart {
		if (adminRef == null) throw new IllegalArgumentException("The admin cannot be null");
		if (dataTypeRef == null) throw new IllegalArgumentException("The datatype cannot be null");
		if (dataOriginInstanceId == null) throw new IllegalArgumentException("The data origin instance cannot be null");
	}
	public DN00DataOriginForPersonColdStart(final DN00OrgAdminOID orgAdminOid,
									   		final DN00DataTypeOID dataTypeOid,final DN00DataOriginInstanceID dataOriginInstanceId) {
		this(new DN00OrgAdminRef(orgAdminOid),
			 new DN00DataTypeRef(dataTypeOid),dataOriginInstanceId);
	}
	public DN00DataOriginForPersonColdStart(final DN00OrgAdminID orgAdminId,
											final DN00DataTypeID dataTypeId,final DN00DataOriginInstanceID dataOriginInstanceId) {
		this(new DN00OrgAdminRef(orgAdminId),
			 new DN00DataTypeRef(dataTypeId),dataOriginInstanceId);
	}
}
