package dena.api.common.model.securitycontext.user;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.model.security.user.User;
import r01f.model.security.user.UserBase;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.PersonID;
import r01f.util.comparable.ComparableObjectUtils;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

@MarshallType(as="personUser")
@ConvertToDirtyStateTrackable
@Accessors(prefix="_")
public class DN00DENAPersonUser 
	 extends UserBase<DN00DENAPersonUser> 
  implements SelfValidates<DN00DENAPersonUser>,
			 DN00IsDENAUser {
	
	    private static final long serialVersionUID = 2327019403550008421L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS                                
/////////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * PersonOID
//	 */
//	@MarshallField(as="personOid",
//				   whenXml=@MarshallFieldAsXml(attr=true))
//	@Getter @Setter private DN00PersonOID _personOid;
	/**
	 * PersonID (ie: DNI, NIE, CIF, etc) of the user
	 */
	@MarshallField(as="personId",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PersonID _personId;
	/**
	 * Something to display about the user 
	 */
	@MarshallField(as="displayName",escape=true)
	@Getter @Setter private String _displayName;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DENAPersonUser() {
		super();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	UPDATE                                                                       
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public <U extends User> boolean updateFrom(final U other) {
		if (other == null) return false;
		if (other instanceof DN00DENAPersonUser) {
			return !this.isSameObject((DN00DENAPersonUser) other);
		}
		return true;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<DN00DENAPersonUser> validate() {
		return DN00DENAUserValidator.<DN00DENAPersonUser>createUserValidator()
									.validate(this);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public boolean isSameObject(final DN00DENAPersonUser  other) {
		return  super.isSameObject(other)
				&& 
				ComparableObjectUtils.compare(_personId,other.getPersonId());
	}
}
