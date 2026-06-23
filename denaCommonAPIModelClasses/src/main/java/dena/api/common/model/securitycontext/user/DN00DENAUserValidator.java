package dena.api.common.model.securitycontext.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.model.PersistableModelObjectValidators;
import r01f.util.types.Strings;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class DN00DENAUserValidator {
/////////////////////////////////////////////////////////////////////////////////////////
//	USER                                                                          
/////////////////////////////////////////////////////////////////////////////////////////	
	public static <U extends DN00IsDENAUser> Validates<U> createUserValidator() {
		return new Validates<U>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<U> validate(final U obj) {
							// super valid
							ObjectValidationResult<U> superValid = PersistableModelObjectValidators.<U>createPersistableObjBaseValidator()
																					   			   .validate(obj);
							
							// Validate the data
							ObjectValidationResult<U> thisValid = null;
							if (Strings.isNullOrEmpty(obj.getDisplayName())) thisValid = ObjectValidationResultBuilder.on(obj)
																													  .isNotValidBecause("The {} object MUST have a display name",
																															  			 obj.getClass().getSimpleName());
							if (thisValid == null) thisValid = ObjectValidationResultBuilder.on(obj)
																		 .isValid();
							// combine
							return ObjectValidationResultBuilder.on(obj)
																.combine(superValid,
																		 thisValid);
						}
		};
	}	
}
