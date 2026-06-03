package dena.api.common.model.search;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.guids.OID;
import r01f.model.IndexableModelObject;
import r01f.model.search.SearchResultItemForModelObjectBase;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;

/**
 * A search result item for an organizational entity like {@link AA14Organization}, {@link AA14Location} or {@link AA14Agent}
 */
@Accessors(prefix="_")
public abstract class DN00DENASearchResultItemBase<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O> & IndexableModelObject,
									    	   	   SELF_TYPE extends DN00DENASearchResultItemBase<O,M,SELF_TYPE>>
	   		  extends SearchResultItemForModelObjectBase<M>
    	   implements DN00IsDENASearchResultItem<O,M> {

	private static final long serialVersionUID = 4169587420774250028L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="oid",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter protected O _oid;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDERS
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DENASearchResultItemBase() {
		// default no-args constructor
	}
	public DN00DENASearchResultItemBase(final Class<M> modelObjectType) {
		super(modelObjectType);
	}
	public DN00DENASearchResultItemBase(final Class<M> modelObjectType,
									final O oid) {
		super(modelObjectType);
		_oid = oid;
	}
	public DN00DENASearchResultItemBase(final M obj) {
		super(obj);
		_oid = obj.getOid();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	OID
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public void unsafeSetOid(final OID oid) {
		_oid = (O)oid;
	}
}
