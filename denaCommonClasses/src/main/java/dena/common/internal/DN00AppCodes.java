package dena.common.internal;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.services.ids.ServiceIDs.ClientApiAppCode;
import r01f.services.ids.ServiceIDs.CoreAppCode;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.types.AppVersion;
import r01f.types.JavaPackage;

@Accessors(prefix="_")
@RequiredArgsConstructor
public class DN00AppCodes {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public static final JavaPackage API_JAVA_PACKAGE = JavaPackage.named("dena.api");
/////////////////////////////////////////////////////////////////////////////////////////
//  APP CODES
/////////////////////////////////////////////////////////////////////////////////////////
	public static final AppVersion API_VERSION = AppVersion.from("0.0.1");
	
	public static final String API_APPCODE_STR = "dn00";
	public static final ClientApiAppCode API_APPCODE = ClientApiAppCode.forId(API_APPCODE_STR);

	public static final String CORE_APPCODE_STR = "dn01";
	public static final CoreAppCode CORE_APPCODE = CoreAppCode.forId(CORE_APPCODE_STR);

	public static final String UI_APPCODE_STR = "dn00ui";
	public static final CoreAppCode UI_APP_CODE = CoreAppCode.forId(UI_APPCODE_STR);

/////////////////////////////////////////////////////////////////////////////////////////
//  ORG
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String ORGCONFIG_MOD_STR = "orgconfig";
	public static final String ORGCONFIG_FQN =  CORE_APPCODE_STR + "." + ORGCONFIG_MOD_STR;
	public static final CoreModule ORGCONFIG_MOD = CoreModule.forId(ORGCONFIG_MOD_STR);
	
/////////////////////////////////////////////////////////////////////////////////////////
//  INTEROPCONFIG
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String INTEROPCONFIG_MOD_STR = "interopconfig";
	public static final String INTEROPCONFIG_FQN =  CORE_APPCODE_STR + "." + INTEROPCONFIG_MOD_STR;
	public static final CoreModule INTEROPCONFIG_MOD = CoreModule.forId(INTEROPCONFIG_MOD_STR);
	
/////////////////////////////////////////////////////////////////////////////////////////
//  PERSON
/////////////////////////////////////////////////////////////////////////////////////////
////////// PERSON
	public static final String PERSON_MOD_STR = "person";
	public static final String PERSON_FQN =  CORE_APPCODE_STR + "." + PERSON_MOD_STR;
	public static final CoreModule PERSON_MOD = CoreModule.forId(PERSON_MOD_STR);

////////// PERSON SYNC	
	public static final String PERSON_SYNC_MOD_STR = "person.sync";	
	public static final String PERSON_SYNC_FQN =  CORE_APPCODE_STR + "." + PERSON_SYNC_MOD_STR;
	public static final CoreModule PERSON_SYNC_MOD = CoreModule.forId(PERSON_SYNC_MOD_STR);
	
/////////////////////////////////////////////////////////////////////////////////////////
//  SYNC AND RETRIEVE
/////////////////////////////////////////////////////////////////////////////////////////
////////// SRMD
	public static final String INTEROP_SYNDRETRIEVE_MOD_STR = "interop.syncandretrieve";
	public static final String INTEROP_SYNCRETRIEVE_FQN =  CORE_APPCODE_STR + "." + INTEROP_SYNDRETRIEVE_MOD_STR;
	public static final CoreModule INTEROP_SYNCRETRIEVE_MOD = CoreModule.forId(INTEROP_SYNDRETRIEVE_MOD_STR);
	
/////////////////////////////////////////////////////////////////////////////////////////
// INTERNAL TOOLS
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String S3FILESTORE_MOD_STR = "s3filestore";
	public static final String S3FILESTORE_FQN =  CORE_APPCODE_STR + "." + S3FILESTORE_MOD_STR;
	public static final CoreModule S3FILESTORE_MOD = CoreModule.forId(S3FILESTORE_MOD_STR);

/////////////////////////////////////////////////////////////////////////////////////////
//	AUDIT REGISTRY
/////////////////////////////////////////////////////////////////////////////////////////
	public static final CoreAppCode AUDIT_REGISTRY_CORE_APPCODE = CORE_APPCODE;
	public static final CoreAppCode AUDIT_LOGGER_CORE_APPCODE = CORE_APPCODE;
	public static final CoreAppCode AUDIT_CONSUMER_CORE_APPCODE = CORE_APPCODE;

//////////	AUDIT REGISTRY
	public static final String AUDIT_REGISTRY_MOD_STR = "audit.registry";
	public static final String AUDIT_REGISTRY_FQN =  CORE_APPCODE_STR + "." + AUDIT_REGISTRY_MOD_STR;
	public static final CoreModule AUDIT_REGISTRY_MOD = CoreModule.forId(AUDIT_REGISTRY_MOD_STR);

////////// AUDIT LOGGER
	public static final String AUDIT_LOGGER_MOD_STR = "audit.logger";
	public static final String AUDIT_LOGGER_FQN =  CORE_APPCODE_STR + "." + AUDIT_LOGGER_MOD_STR;
	public static final CoreModule AUDIT_LOGGER_MOD = CoreModule.forId(AUDIT_LOGGER_MOD_STR);

///////////	AUDIT CONSUMER
	public static final String AUDIT_CONSUMER_MOD_STR = "audit.consumer";
	public static final String AUDIT_CONSUMER_FQN =  CORE_APPCODE_STR + "." + AUDIT_CONSUMER_MOD_STR;
	public static final CoreModule AUDIT_CONSUMER_MOD = CoreModule.forId(AUDIT_CONSUMER_MOD_STR);
	
/////////////////////////////////////////////////////////////////////////////////////////
//  SECURITY
/////////////////////////////////////////////////////////////////////////////////////////
	public static final CoreAppCode SECURITY_SERVICES_EXTERNAL_APPCODE = CORE_APPCODE;
	public static final CoreAppCode SECURITY_SERVICES_WEB_AUTHN_APPCODE = CORE_APPCODE;

////////// SECURITY SERVICES EXTERNAL
	public static final String SECURITY_SERVICES_EXTERNAL_MOD_STR = "security.services.external";
	public static final String SECURITY_SERVICES_EXTERNAL_FQN =  CORE_APPCODE_STR + "." + SECURITY_SERVICES_EXTERNAL_MOD_STR;
	public static final CoreModule SECURITY_SERVICES_EXTERNAL_MOD = CoreModule.forId(SECURITY_SERVICES_EXTERNAL_MOD_STR);
	
///////////	SECURITY WEBAUTHN
	public static final String SECURITY_SERVICES_WEB_AUTHN_MOD_STR = "security.services.webauthn";
	public static final String  SECURITY_SERVICES_WEB_AUTHN_FQN =  CORE_APPCODE_STR + "." + SECURITY_SERVICES_WEB_AUTHN_MOD_STR;
	public static final CoreModule  SECURITY_SERVICES_WEB_AUTHN_MOD = CoreModule.forId(SECURITY_SERVICES_WEB_AUTHN_MOD_STR);

////////// SECURITY CLIENT INSTALLMENT
	public static final String SECURITY_CLIENT_INSTALLMENT_MOD_STR = "security.clientinstallment";
	public static final String SECURITY_CLIENT_INSTALLMENT_FQN =  CORE_APPCODE_STR + "." + SECURITY_CLIENT_INSTALLMENT_MOD_STR;
	public static final CoreModule SECURITY_CLIENT_INSTALLMENT_MOD = CoreModule.forId(SECURITY_CLIENT_INSTALLMENT_MOD_STR);

/////////////////////////////////////////////////////////////////////////////////////////
//	PERSON CLIENT DEVICE
/////////////////////////////////////////////////////////////////////////////////////////
	public static final CoreAppCode PERSON_CLIENT_DEVICE_CORE_APPCODE = CORE_APPCODE;

	public static final String PERSON_CLIENT_DEVICE_MOD_STR = "person.client.device";
	public static final String PERSON_CLIENT_DEVICE_FQN =  CORE_APPCODE_STR + "." + PERSON_CLIENT_DEVICE_MOD_STR;
	public static final CoreModule PERSON_CLIENT_DEVICE_MOD = CoreModule.forId(PERSON_CLIENT_DEVICE_MOD_STR);

}
