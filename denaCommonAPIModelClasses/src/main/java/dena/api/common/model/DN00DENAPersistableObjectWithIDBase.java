package dena.api.common.model;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.guids.OID;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.validation.ObjectValidationResult;

@Accessors(prefix="_")
public abstract class DN00DENAPersistableObjectWithIDBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,
														  SELF_TYPE extends DN00DENAPersistableObjectWithIDBase<O,I,SELF_TYPE>>
     		  extends DN00DENAPersistableObjectBase<O,SELF_TYPE>
  		   implements DN00IsDENAPersistableObjectWithID<O,I>,
			 		  DN00IsDENAModelObject {

	private static final long serialVersionUID = 6236132360420854515L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * A business id
	 */
	@MarshallField(as="id",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter protected I _id;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
    protected DN00DENAPersistableObjectWithIDBase() {
    	super();
    }
    protected DN00DENAPersistableObjectWithIDBase(final O oid) {
		super(oid);
	}
    protected DN00DENAPersistableObjectWithIDBase(final O oid,
												  final I id) {
		super(oid);
		_id = id;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  HasID
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public void unsafeSetId(final OID id) {
		_id = (I)id;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	VALIDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public ObjectValidationResult<SELF_TYPE> validate() {
		return DN00CommonDENAObjectValidators.<SELF_TYPE>createObjectWithIDValidatorBase()
										  	 .validate((SELF_TYPE)this);
	}
}
