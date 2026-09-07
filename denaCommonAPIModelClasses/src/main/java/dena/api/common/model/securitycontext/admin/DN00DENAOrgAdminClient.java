package dena.api.common.model.securitycontext.admin;


import dena.api.common.model.refs.orgconfig.DN00OrgAdminRef;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;

@MarshallType(as="orgAdminClient")
@ConvertToDirtyStateTrackable
@Accessors(prefix="_")
public class DN00DENAOrgAdminClient 	
	implements DN00IsDENAOrgAdminClient {	
	
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS                                
/////////////////////////////////////////////////////////////////////////////////////////
	    
	 @MarshallField(as="orgAdminRef",
		             whenXml=@MarshallFieldAsXml(attr=true))
	 @Getter @Setter private DN00OrgAdminRef _orgAdminRef; // the oid of the admin should be persisted at Keycloack.
	
	/**
	 * Something to display about the org admin *
	 */
    @MarshallField(as="orgName")
    @Getter @Setter private String _orgName;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////
//	                                                              
/////////////////////////////////////////////////////////////////////////////////////////

}
