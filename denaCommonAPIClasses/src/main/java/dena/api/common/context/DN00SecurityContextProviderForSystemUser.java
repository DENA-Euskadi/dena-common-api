package dena.api.common.context;

import jakarta.inject.Provider;
import r01f.guids.CommonOIDs.TenantID;
import r01f.model.security.context.SecurityContextProviderForSystemUserBase;
import r01f.securitycontext.SecurityContext;

/**
 * see {@link SecurityContextProviderForSystemUserBase} for usage
 */
public class DN00SecurityContextProviderForSystemUser
     extends SecurityContextProviderForSystemUserBase
  implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00SecurityContextProviderForSystemUser() {
		
		super(DN00SecurityContext.forSystemUser()); // TODO use any kind of token to prevent the creation of this security token from unlegitimate parts
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  GET
/////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public DN00SecurityContext get() {
		DN00SecurityContext outCtx = DN00SecurityContext.forSystemUser();
		outCtx.setTenantId(TenantID.DEFAULT);
		return outCtx;
	}
}
