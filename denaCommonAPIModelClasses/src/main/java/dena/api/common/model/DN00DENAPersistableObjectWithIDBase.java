package dena.api.common.model;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.experimental.Accessors;
import r01f.model.PersistableModelObjectWithIDBase;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

@Accessors(prefix="_")
public abstract class DN00DENAPersistableObjectWithIDBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>,
														  SELF_TYPE extends DN00DENAPersistableObjectWithIDBase<O,I,SELF_TYPE>>
     		  extends PersistableModelObjectWithIDBase<O,I,SELF_TYPE>
  		   implements DN00IsDENAPersistableObjectWithID<O,I>,
			 		  SelfValidates<SELF_TYPE> {

	private static final long serialVersionUID = 6236132360420854515L;
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
		super(oid,
			  id);
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
