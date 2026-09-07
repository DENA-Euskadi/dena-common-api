package dena.api.common.model.securitycontext.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.model.security.context.SecurityContextAuthenticatedActorForApp;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.securitycontext.SecurityIDS.SecurityToken;

@Accessors(prefix="_")
public class DN00SecurityContextAuthenticatedActorForOrgAdmin 
		extends SecurityContextAuthenticatedActorForApp {

	private static final long serialVersionUID = -1576329590244424496L;
	
	
	@MarshallField(as="orgAdminClien")
	@Getter @Setter  protected  DN00DENAOrgAdminClient _orgAdminClient;
/////////////////////////////////////////////////////////////////////////////////////////
//CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00SecurityContextAuthenticatedActorForOrgAdmin(final SecurityToken clientIdOfOAuthBasedClientCredentials) {
		super(clientIdOfOAuthBasedClientCredentials);		
	}
	

}
