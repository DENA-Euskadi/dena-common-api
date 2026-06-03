package dena.api.common.model.security;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.securitycontext.SecurityIDS.LoginID;
import r01f.securitycontext.SecurityIDS.Password;


@Accessors(prefix="_")
public class DN00SecurityUserPasswordConfig 
  implements DN00IsDENASecurityConfig {

	private static final long serialVersionUID = 456888416160891743L;
//////////////////////////////////////////////////////////////////////////////
//   FIELDS
//////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="loginId")
    @Getter @Setter private LoginID _loginId;  
  
	@MarshallField(as="password")
    @Getter @Setter private Password _password;   

}
