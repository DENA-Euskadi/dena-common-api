package dena.api.common.model.refs.interopconfig;

import dena.api.common.model.oids.interopconfig.DN00InteropConfigIDs.DN00DataOriginInstanceID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigIDs.DN00DataTypeID;
import dena.api.common.model.oids.interopconfig.DN00InteropConfigOIDs.DN00DataTypeOID;
import lombok.Getter;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * A data carrier for [data type] and [data origin instance] references
 */
@MarshallType(as="dataTypeAndDataOriginInstance")
public record DN00DataTypeAndDataOriginInstance(@MarshallField(as="dataTypeRef")
												@Getter DN00DataTypeRef dataTypeRef,
												
												@MarshallField(as="dataOriginInstanceId",
															   whenXml=@MarshallFieldAsXml(attr=true))
												@Getter DN00DataOriginInstanceID dataOriginInstanceId) {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DataTypeAndDataOriginInstance {
		if (dataTypeRef == null) throw new IllegalArgumentException("The datatype cannot be null");
		if (dataOriginInstanceId == null) throw new IllegalArgumentException("The data origin instance cannot be null");
	}
	public DN00DataTypeAndDataOriginInstance(final DN00DataTypeOID dataTypeOid,
											 final DN00DataOriginInstanceID dataOriginInstanceId) {
		this(new DN00DataTypeRef(dataTypeOid),
			 dataOriginInstanceId);
	}
	public DN00DataTypeAndDataOriginInstance(final DN00DataTypeID dataTypeId,
											 final DN00DataOriginInstanceID dataOriginInstanceId) {
		this(new DN00DataTypeRef(dataTypeId),
			 dataOriginInstanceId);
	}
	public static DN00DataTypeAndDataOriginInstance from(final DN00DataTypeRef dataTypeRef,final DN00DataOriginInstanceID dataOriginInstanceId) {
		return new DN00DataTypeAndDataOriginInstance(dataTypeRef,dataOriginInstanceId);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public DN00DataTypeOID getDataTypeOid() {
		return this.getDataTypeRef() != null ? this.getDataTypeRef().getOid() : null;
	}
	public DN00DataTypeID getDataTypeId() {
		return this.getDataTypeRef() != null ? this.getDataTypeRef().getId() : null;
	}
}
