package dena.api.common.model.summaries;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.facets.LangInDependentNamed;
import r01f.guids.OID;
import r01f.model.ModelObjectTracking;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.util.types.Strings;

@Accessors(prefix="_")
@EqualsAndHashCode
public abstract class DN00SummarizedDENAPersistableObjectBase<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>,
												    	  	  SELF_TYPE extends DN00SummarizedDENAPersistableObjectBase<O,M,SELF_TYPE>>
		   implements DN00IsSummarizedDENAPersistableObject<O,M>,
		   			  LangInDependentNamed {

	private static final long serialVersionUID = -8203773765925528330L;

/////////////////////////////////////////////////////////////////////////////////////////
//  NON SERIALIZABLE FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter protected final transient Class<M> _modelObjectType;
/////////////////////////////////////////////////////////////////////////////////////////
//  SERIALIZABLE FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="oid",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter protected O _oid;
	
	@MarshallField(as="trackingInfo")
	@Getter @Setter protected ModelObjectTracking _trackingInfo;
	
	@MarshallField(as="name",escape=true)
	@Getter @Setter protected String _name;	
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	protected DN00SummarizedDENAPersistableObjectBase(final Class<M> modelObjType) {
		_modelObjectType = modelObjType;
	}
	@SuppressWarnings("unchecked")
	protected DN00SummarizedDENAPersistableObjectBase(final M obj) {
		this((Class<M>)obj.getClass());
		_oid = obj.getOid();
		_trackingInfo = obj.getTrackingInfo();
	}
	protected DN00SummarizedDENAPersistableObjectBase(final DN00IsSummarizedDENAPersistableObject<O,M> other) {
		_modelObjectType = other.getModelObjectType();
		_oid = other.getOid();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FLUENT API
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public SELF_TYPE withOid(final O oid) {
		_oid = oid;
		return (SELF_TYPE)this;
	}
	@SuppressWarnings("unchecked")
	public SELF_TYPE withName(final String name) {
		_name = name;
		return (SELF_TYPE)this;
	}
	@Override
	public LangInDependentNamed asLangInDependentNamed() {
		return this;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  HasOID
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public void unsafeSetOid(final OID oid) {
		_oid = (O)oid;
	}
	protected void setCommonFields(final M obj) {
		_oid = obj.getOid();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  DEBUG
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		return Strings.customized("{}: oid={} name={}",
								  _modelObjectType.getSimpleName(),_oid,_name);
	}
}
