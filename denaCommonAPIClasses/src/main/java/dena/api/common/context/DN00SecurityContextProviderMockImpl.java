package dena.api.common.context;



import dena.api.common.model.securitycontext.user.DN00DENAManagementUser;
import dena.api.common.model.securitycontext.user.DN00IsDENAUser;
import jakarta.inject.Provider;
import r01f.guids.CommonOIDs.TenantID;
import r01f.locale.Language;
import r01f.model.security.context.SecurityContextAuthenticatedActorBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.securitycontext.SecurityIDS.LoginID;
import r01f.securitycontext.SecurityIDS.SecurityProviderID;
import r01f.securitycontext.SecurityOIDs.UserOID;

/**
 * Mock provider for user contexts
 */
public class DN00SecurityContextProviderMockImpl
  implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//  Provider
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public DN00SecurityContext get() {
		DN00IsDENAUser user = new DN00DENAManagementUser();
		user.setOid(UserOID.supply());
		user.setDisplayName("Mock User");
		user.setPrefLang(Language.DEFAULT);
		DN00SecurityContext outCtx = new DN00SecurityContext(SecurityContextAuthenticatedActorBuilder.forUser(user)
																									 .using(SecurityProviderID.USER_PASSWORD)
																									 .loggedInWith(LoginID.forId("mock-user-login")));
		outCtx.setTenantId(TenantID.DEFAULT);
		return outCtx;
	}
}
