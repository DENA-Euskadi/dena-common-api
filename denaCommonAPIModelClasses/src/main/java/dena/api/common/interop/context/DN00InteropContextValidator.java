package dena.api.common.interop.context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class DN00InteropContextValidator {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public static Validates<DN00InteropContext> createContextValidator() {
		return new Validates<DN00InteropContext>() {
						@Override
						public ObjectValidationResult<DN00InteropContext> validate(final DN00InteropContext ctx) {
							/////////// --- MESSAGE
							if (ctx.getMessageType() == null) return ObjectValidationResultBuilder.on(ctx)
																								  .isNotValidBecause("message type cannot be null");
							if (ctx.getMessageCorrelationId() == null) return ObjectValidationResultBuilder.on(ctx)
																								  		   .isNotValidBecause("[correlation id] cannot be null");

							////////// --- OFIGIN
							if (ctx.getOriginClientInstallmentOid() != null
							 && ctx.getOriginAdmin() != null) return ObjectValidationResultBuilder.on(ctx)
																							    .isNotValidBecause("the message origin can be the [client installment], [administration] or [dena] BUT not two of them");
							if (ctx.getOriginClientInstallmentOid() != null
							 && ctx.getOriginParty().isNOT(DN00InteropMessageParty.CLIENT_INSTALLMENT)) return ObjectValidationResultBuilder.on(ctx)
																																  .isNotValidBecause("the message type " + ctx.getMessageType() + " cannot be from a [client installment]");
							if (ctx.getOriginAdmin() != null
							 && ctx.getOriginParty().isNOT(DN00InteropMessageParty.ADMIN)) return ObjectValidationResultBuilder.on(ctx)
																														  	   .isNotValidBecause("the message type " + ctx.getMessageType() + " cannot be from an [admin]");
							if (ctx.getOriginParty().is(DN00InteropMessageParty.CLIENT_INSTALLMENT)
							 && ctx.getOriginClientInstallmentOid() == null) return ObjectValidationResultBuilder.on(ctx)
																											   .isNotValidBecause("the message type " + ctx.getMessageType() + " is from [client installment] but the [client installment] is null");
							if (ctx.getOriginParty().is(DN00InteropMessageParty.ADMIN)
							 && ctx.getOriginAdmin() == null) return ObjectValidationResultBuilder.on(ctx)
																							    .isNotValidBecause("the message type " + ctx.getMessageType() + " is from an [admin] but the [admin] is null");
							if (ctx.getDestinationParty().is(DN00InteropMessageParty.ADMIN)
							 && ctx.getDestinationAdmin() == null) return ObjectValidationResultBuilder.on(ctx)
																									   .isNotValidBecause("the message type " + ctx.getMessageType() + " is destinated to an [admin] the [admin] ref is null");
							// valid
							return ObjectValidationResultBuilder.on(ctx)
															    .isValid();
						}
					};
	}

}
