package dena.api.common.model.security;


import dena.api.common.model.oids.security.DN00SecurityIDs.DN00SecurityClientID;
import dena.api.common.model.oids.security.DN00SecurityIDs.DN00SecurityIDPID;
import dena.api.common.model.oids.security.DN00SecurityIDs.DN00SecurityToken;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.types.url.Url;


@Accessors(prefix="_")
public class DN00SecurityOAuthConfig 
  implements DN00IsDENASecurityConfig {

	private static final long serialVersionUID = 456888416160891743L;
//////////////////////////////////////////////////////////////////////////////
//   FIELDS
//////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="idpEndPointUrl")
    @Getter @Setter private Url _idpEndPointUrl;  // Endpoint API URL for this configuration.
  
	@MarshallField(as="idpId")
    @Getter @Setter private DN00SecurityIDPID _idpId;   // Keycloak Id

    // Client id associated with the configuration.
	@MarshallField(as="clientId")
    @Getter @Setter private DN00SecurityClientID _clientId;

    // Secret id associated with the configuration.
	@MarshallField(as="secret")
    @Getter @Setter private DN00SecurityToken _secret;

}
