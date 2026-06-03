package dena.api.common.model;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.model.PersistableModelObjectBase;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

/**
 * Base class for all persistable model objects of the DENA domain
 * @param <O>
 * @param <SELF_TYPE>
 */
public abstract class DN00DENAPersistableObjectBase<O extends DN00IsDENAPersistableObjectOID,
											  		SELF_TYPE extends DN00DENAPersistableObjectBase<O,SELF_TYPE>>
		 	  extends PersistableModelObjectBase<O,SELF_TYPE>
 	  	   implements DN00IsDENAPersistableObject<O>,
 	  	   			  SelfValidates<SELF_TYPE> {

	private static final long serialVersionUID = -2898186655435119389L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DENAPersistableObjectBase() {
		super();
	}
	public DN00DENAPersistableObjectBase(final O oid) {
		super(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	VALIDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public ObjectValidationResult<SELF_TYPE> validate() {
		return DN00CommonDENAObjectValidators.<SELF_TYPE>createObjectValidatorBase()
										  	 .validate((SELF_TYPE)this);
	}
}
