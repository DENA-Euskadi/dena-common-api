package dena.api.common.model;

import lombok.NoArgsConstructor;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00CommonDENAObjectValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE
/////////////////////////////////////////////////////////////////////////////////////////
	public static <P extends DN00IsDENAPersistableObject<?>> Validates<P> createObjectValidatorBase() {
		return new Validates<>() {
						@Override
						public ObjectValidationResult<P> validate(final P obj) {
							if (obj.getOid() == null) return ObjectValidationResultBuilder.on(obj)
																						  .isNotValidBecause("The oid is mandatory");
							return ObjectValidationResultBuilder.on(obj)
																.isValid();
						}
			   };
	}
	public static <P extends DN00IsDENAPersistableObjectWithID<?,?>> Validates<P> createObjectWithIDValidatorBase() {
		return new Validates<>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<P> validate(final P obj) {
							// super valid?
							ObjectValidationResult<P> superValidResult = DN00CommonDENAObjectValidators.<P>createObjectValidatorBase()
																									   .validate(obj);
							// this valid?
							ObjectValidationResult<P> thisValidResult = null;
							if (obj.getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																			   .isNotValidBecause("The id is mandatory");
							} else {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																			   .isValid();
							}
							// combine
							return ObjectValidationResultBuilder.on(obj)
																.combine(superValidResult,
																		 thisValidResult);
						}
			   };
	}
}
