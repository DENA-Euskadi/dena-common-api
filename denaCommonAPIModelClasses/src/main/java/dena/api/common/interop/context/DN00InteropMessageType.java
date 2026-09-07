package dena.api.common.interop.context;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.enums.EnumExtended;

/**
 * Message types in DENA interoperability protocol.
 */
@Accessors(prefix="_")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DN00InteropMessageType
 implements EnumExtended<DN00InteropMessageType>{	
	////////// Client security
	CLIENT_LOGIN_DEMO(DN00InteropFlowDirection.REQUEST,
					  DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
	CLIENT_LOGIN(DN00InteropFlowDirection.REQUEST,
				 DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_PASSKEY_CLEAN_CREDENTIALS(DN00InteropFlowDirection.REQUEST,
    								 DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_PASSKEY_LOGIN_INIT(DN00InteropFlowDirection.REQUEST,
    						  DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_PASSKEY_LOGIN_FINISH(DN00InteropFlowDirection.RESPONSE,
    							DN00InteropMessageParty.DENA_CORE,DN00InteropMessageParty.CLIENT_INSTALLMENT),
    CLIENT_PASSKEY_REGISTER_INIT(DN00InteropFlowDirection.REQUEST,
    							 DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_PASSKEY_REGISTER_FINISH(DN00InteropFlowDirection.RESPONSE,
    							   DN00InteropMessageParty.DENA_CORE,DN00InteropMessageParty.CLIENT_INSTALLMENT),
    
    ////////// Client Init
    CLIENT_INIT_REQ(DN00InteropFlowDirection.REQUEST,
    				DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_INIT_RESP(DN00InteropFlowDirection.RESPONSE,
    				 DN00InteropMessageParty.DENA_CORE,DN00InteropMessageParty.CLIENT_INSTALLMENT),
    
	////////// SRMD SYNC
    CLIENT_SRMD_SYNC_REQ(DN00InteropFlowDirection.REQUEST,
    					 DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_SRMD_SYNC_RESP(DN00InteropFlowDirection.RESPONSE,
    					  DN00InteropMessageParty.DENA_CORE,DN00InteropMessageParty.CLIENT_INSTALLMENT),
    
    ADMIN_SRMD_SYNC_REQ(DN00InteropFlowDirection.REQUEST,
    					DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    ADMIN_SRMD_SYNC_RESP(DN00InteropFlowDirection.RESPONSE,
    					 DN00InteropMessageParty.DENA_CORE,DN00InteropMessageParty.ADMIN),
    
    ////////// Client Retrieve
    CLIENT_RETRIEVE_REQ(DN00InteropFlowDirection.REQUEST,
    					DN00InteropMessageParty.CLIENT_INSTALLMENT,DN00InteropMessageParty.DENA_CORE),
    CLIENT_RETRIEVE_RESP(DN00InteropFlowDirection.RESPONSE,
    					 DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.CLIENT_INSTALLMENT),
    
    ////////// TODO REVIEW!!!
    ADMIN_PERSON_PULL_BESPOKE_CREATE_REQ(DN00InteropFlowDirection.REQUEST,
    						  			 DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    ADMIN_PERSON_PULL_BESPOKE_FETCH(DN00InteropFlowDirection.REQUEST,
    					   		    DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    ADMIN_PERSON_BESPOKE_EXPORT_ASSET_FETCH(DN00InteropFlowDirection.REQUEST,
    						   				DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    ADMIN_PERSON_PREGEN_EXPORT_ASSET_FETCH(DN00InteropFlowDirection.REQUEST,
    						  			   DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    
  ////////// --- PERSON
    PERSON_ADMIN_SEARCH(DN00InteropFlowDirection.REQUEST,
    					DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    PERSON_FETCH_DATA(DN00InteropFlowDirection.REQUEST,
    				  DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE),
    PERSON_ADMIN_HEAD(DN00InteropFlowDirection.REQUEST,
    				  DN00InteropMessageParty.ADMIN,DN00InteropMessageParty.DENA_CORE);

	@Getter private final DN00InteropFlowDirection _flowDirection;
	
	@Getter private final DN00InteropMessageParty _originParty;
	@Getter private final DN00InteropMessageParty _destinationParty;
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
	public DN00InteropFlowDirection flowDirection() {
		return _flowDirection;
	}
	public DN00InteropMessageParty originParty() {
		return _originParty;
	}
	public DN00InteropMessageParty destinationParty() {
		return _destinationParty;
	}
}
