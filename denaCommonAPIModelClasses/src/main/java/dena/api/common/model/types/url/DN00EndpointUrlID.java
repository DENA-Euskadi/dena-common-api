package dena.api.common.model.types.url;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;



public record  DN00EndpointUrlID (String id) 
		implements DN00IsDENAObjectID {	
/////////////////////////////////////////////////////////////////////////////////////////

 ///////////////////////////////////////////////////////////////////////////////////////// 
    public static DN00EndpointUrlID valueOf(final String id) {
        return new DN00EndpointUrlID(id);
    }
    public static DN00EndpointUrlID forId(final String id) {
        return new DN00EndpointUrlID(id);
    }
    
}
