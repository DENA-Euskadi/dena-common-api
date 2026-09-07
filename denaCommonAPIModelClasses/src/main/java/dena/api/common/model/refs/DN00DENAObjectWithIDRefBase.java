package dena.api.common.model.refs;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.util.types.Objects;

@Accessors(prefix="_")
public abstract class DN00DENAObjectWithIDRefBase<O extends DN00IsDENAPersistableObjectOID,I extends DN00IsDENAPersistableObjectID<O>>
			  extends DN00DENAObjectRefBase<O>
           implements DN00IsDENAObjectWithIDRef<O,I> {

    private static final long serialVersionUID = -3167331914287689392L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    @MarshallField(as="id",
				   whenXml=@MarshallFieldAsXml(attr=true))
    @Getter @Setter protected I _id;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    public DN00DENAObjectWithIDRefBase() {
		// default no-args constructor
	}
    public <R extends DN00DENAObjectWithIDRefBase<O,I>> DN00DENAObjectWithIDRefBase(final R other) {
    	super(other);
		_id = other.getId();
    }
    public DN00DENAObjectWithIDRefBase(final O oid) {
    	super(oid);
    }
    public DN00DENAObjectWithIDRefBase(final I id) {
    	super();
    	_id = id;
    }
	public DN00DENAObjectWithIDRefBase(final O oid,final I id) {
		this(oid);
		_id = id;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	AS STRING
/////////////////////////////////////////////////////////////////////////////////////////    
	@Override
	public String asString() {
		return String.format("%s;%s",
							 this.getOid(),this.getId());
	}
	@Override
	public String toString() {
		return this.asString();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EQUALS & HASHCODE	
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public boolean equals(final Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (other.getClass() != this.getClass()) return false;
		
		DN00DENAObjectWithIDRefBase<?,?> otherRef = (DN00DENAObjectWithIDRefBase<?,?>)other;
		return super.equals(otherRef)
			&& Objects.areEqual(this.getId(),otherRef.getId(),
								(i1,i2) -> i1.is(i2));
	}
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(),
							this.getId());
	}
}
