package dena.api.common.context;

import java.util.Collection;

import dena.api.common.model.interop.context.DN00InteropFlowDirection;
import dena.api.common.model.interop.context.DN00InteropMessageType;
import dena.api.common.model.interop.context.DN00IteropRouteDataItem;
import dena.api.common.model.interop.oids.DN00InteropIDs.DN00InteropDestinationPartyID;
import dena.api.common.model.interop.oids.DN00InteropIDs.DN00InteropOriginPartyID;
import dena.api.common.model.interop.oids.DN00InteropOIDs.DN00MessageCorrelationOID;
import dena.api.common.model.oids.security.DN00SecurityOIDs.DN00ClientInstallmentOID;
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
 * R01M API {@link UserContext} implementation
 */
@MarshallType(as="securityContext")
@Accessors(prefix="_")
public class DN00SecurityContext
     extends SecurityContextBase {

	private static final long serialVersionUID = 1315691985185065475L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////	
////////// ----- MESSAGE    
    @MarshallField(as="messageType")
    @Getter @Setter private DN00InteropMessageType _messageType;
    
    @MarshallField(as="messageCorrelationId") //its a oid
    @Getter @Setter private DN00MessageCorrelationOID _messageCorrelationId;
////////// ----- FLOW DATA
    @MarshallField(as="flowDirection")
    @Getter @Setter private DN00InteropFlowDirection _flowDirection;
    
    @MarshallField(as="originPartyId")
    @Getter @Setter private DN00InteropOriginPartyID  _originPartyId;

    @MarshallField(as="destinationPartyId")
    @Getter @Setter private DN00InteropDestinationPartyID _destinationPartyId; 
    
    @MarshallField(as="interopRouteData")
    @Getter @Setter private Collection<DN00IteropRouteDataItem> _interopRouteData;
////////// -----  USER AGENT
    @MarshallField(as="clientDeviceOid")
    @Getter @Setter private DN00ClientInstallmentOID _clientInstallmentOid;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	protected DN00SecurityContext() {
		super();
	}
////////// For master system
	public static DN00SecurityContext forSystemUser() {
		return new DN00SecurityContext(SecurityContextAuthenticatedActorBuilder.forSystem());
	}
////////// From authentitcated actor
	public DN00SecurityContext(final SecurityContextAuthenticatedActor authActor) {
		this(authActor,
			 TenantID.DEFAULT);
	}
	public DN00SecurityContext(final SecurityContextAuthenticatedActor authActor,
						   	   final TenantID tenantId) {
		super(authActor,
			  tenantId);
	}
	public DN00SecurityContext(final SecurityProviderID securityProviderId,final LoginID loginId,
							   final User user) {
		super(SecurityContextAuthenticatedActorBuilder.forUser(user)
													  .using(securityProviderId)
													  .loggedInWith(loginId));
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
