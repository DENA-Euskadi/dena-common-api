package dena.api.common.context;

import dena.api.common.interop.context.DN00InteropContext;
import dena.api.common.model.DN00IsDENAModelObject;
import dena.api.common.model.oids.security.DN00SecurityOIDs.DN00ClientInstallmentOID;
import dena.api.common.model.refs.orgconfig.DN00OrgAdminRef;
import dena.api.common.model.refs.person.DN00PersonRef;
import dena.api.common.model.securitycontext.user.DN00IsDENAUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.guids.CommonOIDs.TenantID;
import r01f.locale.Language;
import r01f.model.security.context.SecurityContextAuthenticatedActorBuilder;
import r01f.model.security.context.SecurityContextBase;
import r01f.model.security.user.User;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.securitycontext.SecurityContextAuthenticatedActor;
import r01f.securitycontext.SecurityIDS.LoginID;
import r01f.securitycontext.SecurityIDS.SecurityProviderID;

/**
 * API {@link UserContext} implementation
 */
@MarshallType(as="securityContext")
@Accessors(prefix="_")
public class DN00SecurityContext
     extends SecurityContextBase 
  implements DN00IsDENAModelObject {

	private static final long serialVersionUID = 1315691985185065475L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////	
////////// -----  CLIENT INSTALLMENT
    /**
     * The client installment subject of the security context if the security context is for a CLIENT INSTALLMENT user
     */
    @MarshallField(as="clientInstallmentOid")
    @Getter @Setter private DN00ClientInstallmentOID _clientInstallmentOid;
////////// -----  ADMIN
    /**
     * The admin subject of the security context if the security context is for an ADMIN user
     */
    @MarshallField(as="admin")
    @Getter @Setter private DN00OrgAdminRef _admin;
    
////////// ----  PERSON
    /**
     * The person subject of the security context 
     * BEWARE that the User and the Person are NOT the same thing:
     * 			- The user is a SECURITY concept
     * 			- The Person is a BUSINESS concept
     * ... and the might NOT be the same:
     * 			- When an ADMIN is sending DATA about a Person:
     * 					- User: the authenticated admin
     * 					- Person: the Person which the [data] is about
     * 			- When a [client installment] is requesting [data] of a certain [data type] to an [admin] through the [sync and retrieve] CORE component
     * 					- User: the authenticated [client installment] (NOT the Person)
     * 					- Person: the Person which the [data] is about
     */
    @MarshallField(as="person")
    @Getter @Setter private DN00PersonRef _person; 
    
////////// -----  INTEROP CONTEXT
    /**
     * Interop context
     */
    @MarshallField(as="interopContext")
    @Getter @Setter private DN00SecurityContextInteropContext _interopContext;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	protected DN00SecurityContext() {
		super();
	}
////////// ---  For master system
	public static DN00SecurityContext forSystemUser() {
		return new DN00SecurityContext(SecurityContextAuthenticatedActorBuilder.forSystem());
	}
////////// ---  From authenticated actor
	public DN00SecurityContext(final SecurityContextAuthenticatedActor authActor) {
		this(authActor,
			 TenantID.DEFAULT,
			 null);	// no interop context
	}
	public DN00SecurityContext(final SecurityContextAuthenticatedActor authActor,
						   	   final TenantID tenantId) {
		this(authActor,
			 tenantId,
			 null);	// no interop context
	}
	public DN00SecurityContext(final SecurityProviderID securityProviderId,final LoginID loginId,
							   final User user) {
		super(SecurityContextAuthenticatedActorBuilder.forUser(user)
													  .using(securityProviderId)
													  .loggedInWith(loginId));
	}
////////// --- From [interop context]
	public DN00SecurityContext(final SecurityContextAuthenticatedActor authActor,
							   final DN00InteropContext interopContext) {
		this(authActor,
		 	 TenantID.DEFAULT,
			 interopContext);
	}
	public DN00SecurityContext(final SecurityContextAuthenticatedActor authActor,
							   final TenantID tenantId,
							   final DN00InteropContext interopContext) {
		super(authActor,
			  tenantId);
		_interopContext = interopContext != null ? new DN00SecurityContextInteropContext(interopContext)
												 : null;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	LANGUAGE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Language getPrefLang() {
		if (!this.isForUser()) throw new UnsupportedOperationException("only USER [security context] has pref lang");
		return this.getPrefLangOrDefault(Language.DEFAULT);
	}
	@Override
	public Language getPrefLangOrDefault(final Language def) {
		if (!this.isForUser()) throw new UnsupportedOperationException("only USER [security context] has pref lang");
		DN00IsDENAUser user = this.getUser(DN00IsDENAUser.class);
		return user != null
			&& user.getPrefLang() != null
						? user.getPrefLang()
						: def;
	}
}
