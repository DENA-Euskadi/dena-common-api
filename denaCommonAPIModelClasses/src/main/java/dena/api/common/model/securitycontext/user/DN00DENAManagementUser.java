package dena.api.common.model.securitycontext.user;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.guids.CommonOIDs.WorkPlaceCode;
import r01f.model.security.user.User;
import r01f.model.security.user.UserBase;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.comparable.ComparableObjectUtils;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

@MarshallType(as="internalUser")
@ConvertToDirtyStateTrackable
@Accessors(prefix="_")
public class DN00DENAManagementUser 
	 extends UserBase<DN00DENAManagementUser> 
  implements SelfValidates<DN00DENAManagementUser>,
			 DN00IsDENAUser {
	
	private static final long serialVersionUID = 6811283082734109884L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS                                
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * WorkPlace
	 */
	@MarshallField(as="workPlaceCode",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private WorkPlaceCode _workPlaceCode;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DENAManagementUser() {
		super();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	UPDATE                                                                       
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public <U extends User> boolean updateFrom(final U other) {
		if (other == null) return false;
		if (other instanceof DN00DENAManagementUser) {
			return !this.isSameObject((DN00DENAManagementUser) other);
		}
		return true;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<DN00DENAManagementUser> validate() {
		return DN00DENAUserValidator.<DN00DENAManagementUser>createUserValidator()
									.validate(this);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public boolean isSameObject(final DN00DENAManagementUser  other) {
		return  super.isSameObject(other)
				&& 
				ComparableObjectUtils.compare(_workPlaceCode, other.getWorkPlaceCode());
	}
}
