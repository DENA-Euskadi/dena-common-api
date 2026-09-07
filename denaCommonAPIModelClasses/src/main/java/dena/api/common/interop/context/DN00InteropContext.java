	package dena.api.common.interop.context;

import java.util.Collection;
import java.util.stream.Stream;

import dena.api.common.context.DN00SecurityContext;
import dena.api.common.context.DN00SecurityContextInteropContext;
import dena.api.common.model.DN00IsDENAModelObject;
import dena.api.common.model.oids.interop.DN00InteropIDs.DN00InteropComponent;
import dena.api.common.model.oids.interop.DN00InteropOIDs.DN00MessageCorrelationOID;
import dena.api.common.model.oids.security.DN00SecurityOIDs.DN00ClientInstallmentOID;
import dena.api.common.model.refs.interopconfig.DN00DataTypeRef;
import dena.api.common.model.refs.orgconfig.DN00OrgAdminRef;
import dena.api.common.model.refs.person.DN00PersonRef;
import lombok.Getter;
import r01f.debug.Debuggable;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.securitycontext.SecurityContext;
import r01f.types.JsonString;
import r01f.util.types.Strings;


/**
 * Context for all interop messages
 * @see DN00InteropContextBuilder
 * @see DN00InteropContextValidator
 */
@MarshallType(as = "interopContext")
public record DN00InteropContext(////////// ----- MESSAGE DATA   
								    /**
								     * Info about the message:
								     * 	- message type
								     * 	- correlation id
								     * 	- flow direction
								     * 	- route data
								     */
								    @MarshallField(as="message")
								    @Getter DN00InteropMessageData messageData,
							     ////////// ----- ORIGIN PARTY (just ONE of the following fields is set)
								    /**
								     * If the message is sent by a client device, this field contains the OID of the client device that sent the message
								     * If the message is sent by an admin, this field is NULL
								     */
								    @MarshallField(as="originClientInstallment")
								    @Getter DN00ClientInstallmentOID originClientInstallmentOid,
								    /**
								     * If the message is sent by an admin, this field contains the OID of the admin that sent the message
								     * If the message is sent by a [client installment], this field is NULL
								     */
								    @MarshallField(as="originAdmin")
								    @Getter DN00OrgAdminRef originAdmin,
								 ////////// ----- DESTINATION PARTY (just ONE of the following fields is set)
								    /**
								     * The destination admin (if any)
								     * (if this field is null, the message can be destinated to DENA-CORE or to a [client installment])
								     */
								    @MarshallField(as="destinationAdmin")
								    @Getter DN00OrgAdminRef destinationAdmin,
								 ////////// --- ABOUT PERSON    
								    /**
								     * The person subject of the message (if any)
								     */
								    @MarshallField(as="subjectPerson")
								    @Getter DN00PersonRef subjectPerson,
								    /**
								     * The data type subject of the message (if any)
								     * If the message is about a [data type], this field contains the [data type] that the message is about
								     * 		- If the message is sent by an [admin] on RESPONSE of a DATA RETRIEVAL request, this field contains the [data type] that is being sent
								     * 		- If the message is sent by a [client installment] REQUESTING the RETRIEVAL of data from and [admin], this field contains the [data type] that is being requested
								     * 		- Otherwise, this field is null
								     */
								    @MarshallField(as="dataType")
								    @Getter DN00DataTypeRef aboutDataType,
							     ////////// ----- USER AGENT
									/**
									 *  json contained user agent info (see r01f.web.useragent.UserAgentParser for creating the json string from the user agent header)
								     * this field is NOT sent by the client, it is set by the server when the request is received
								     * by using r01f.web.useragent.UserAgentParser.userAgentPropertiesAsJson(httpRequest)
								     * the user agent info sent at the "user-agent" http request header MUST contain the app version
								     * (ie: "DENA/1.0.0 (Windows NT 10.0; Win64; x64) ...") so the server can parse it and extract the app version
								     */
								    @MarshallField(as="userAgent")
								    @Getter JsonString userAgent) 
  implements DN00IsDENAModelObject,
  			 Debuggable {
	
	private static final long serialVersionUID = 7570079814902630391L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
////////// --- From client installment
	public static DN00InteropContext fromClientInstallment(final DN00InteropMessageType messageType,final DN00MessageCorrelationOID messageCorrelationId,
										  				   final DN00ClientInstallmentOID originClientInstallmentOid,
										  				   final DN00OrgAdminRef destinationAdmin,
										  				   final DN00PersonRef subjectPerson,
										  				   final DN00DataTypeRef aboutDataType,
										  				   final JsonString userAgent) {
		return DN00InteropContextBuilder.createMessageOfType(messageType)
									    .withCorrelationOID(messageCorrelationId)
									    .fromClientInstallment(originClientInstallmentOid)
									    .destinatedTo(destinationAdmin)
									    .withSubjectPerson(subjectPerson)
									    .aboutDataType(aboutDataType)
									    .usingUserAgent(userAgent)
									    .build();
	}
////////// --- From Admin
	public static DN00InteropContext fromClientInstallment(final DN00InteropMessageType messageType,final DN00MessageCorrelationOID messageCorrelationId,
										  				   final DN00OrgAdminRef originAdminRef,
										  				   final DN00PersonRef subjectPerson,
										  				   final DN00DataTypeRef aboutDataType,
										  				   final JsonString userAgent) {
		return DN00InteropContextBuilder.createMessageOfType(messageType)
									    .withCorrelationOID(messageCorrelationId)
									    .fromAdmin(originAdminRef)
									    .withSubjectPerson(subjectPerson)
									    .aboutDataType(aboutDataType)
									    .usingUserAgent(userAgent)
									    .build();
	}
////////// --- From SecurityContext
	public static DN00InteropContext from(final SecurityContext securityContext) {
		return DN00InteropContext.from(securityContext.as(DN00SecurityContext.class));
	}
    public static DN00InteropContext from(final DN00SecurityContext securityContext) {
    	if (securityContext == null) throw new IllegalArgumentException("The [security context] is null");
    	if (securityContext.getInteropContext() == null) throw new IllegalArgumentException("The [security context] has no [interop context]");
    	
    	DN00SecurityContextInteropContext interopContext = securityContext.getInteropContext();
    	return new DN00InteropContext(DN00InteropMessageData.modifiableCopyOf(interopContext.getMessageData()),	// BEWARE! can be further modified by the interop message processing
						    		  // Origin party
						    		  securityContext.getClientInstallmentOid(),
						    		  securityContext.getAdmin(),
						    		  // destination party
									  interopContext.getDestinationAdmin(),
									  // subject person
									  interopContext.getSubjectPerson(),
									  // about data type
									  interopContext.getAboutDataType(),
									  // user agent
									  interopContext.getUserAgent());
    }
///////////////////////////////////////////////////////////////////////////////////////////
//	MESSAGE DATA
/////////////////////////////////////////////////////////////////////////////////////////
////////// --- MESSAGE TYPE
    public DN00InteropMessageType getMessageType() {
		return this.messageData != null ? this.messageData.getMessageType()
									    : null;
	}
    public boolean isMessageOfType(final DN00InteropMessageType type) {
    	return this.messageData != null
			&& this.messageData.isMessageOfType(type);
    }
    public DN00MessageCorrelationOID getMessageCorrelationId() {
		return this.messageData != null ? this.messageData.getMessageCorrelationId()
									: null;
    }
////////// --- FLOW DIRECCTION    
    public DN00InteropFlowDirection getFlowDirection() {
    	return this.messageData != null ? this.messageData.getFlowDirection()
									    : null;
    }
    public boolean isMessageWithDirection(final DN00InteropFlowDirection flowDirection) {
		return this.messageData != null
			&& this.messageData.isMessageWithDirection(flowDirection);
    }
////////// --- ORIGIN & DESTINATION PARTY    
    public DN00InteropMessageParty getOriginParty() {
		return this.getMessageType() != null ? this.getMessageType().getOriginParty()
											 : null;
	}
	public DN00InteropMessageParty getDestinationParty() {
		return this.getMessageType() != null ? this.getMessageType().getDestinationParty()
											 : null;
	}
    public boolean isFromClientInstallment() {
		return this.getOriginParty() != null ? this.getOriginParty().is(DN00InteropMessageParty.CLIENT_INSTALLMENT)
											 : this.originClientInstallmentOid != null;
	}
    public boolean isFromAdministration() {
    	return this.getOriginParty() != null ? this.getOriginParty().is(DN00InteropMessageParty.ADMIN)
										 	 : this.originAdmin != null;
    }
    public boolean isFromDENACORE() {
    	return this.getOriginParty() != null ? this.getOriginParty().is(DN00InteropMessageParty.DENA_CORE)
    										 : this.originClientInstallmentOid == null 
    										&& this.originAdmin == null;
    }
    public boolean isDestinatedToAdministration() {
		return this.getDestinationParty() != null ? this.getDestinationParty().is(DN00InteropMessageParty.ADMIN)
										  		  : this.destinationAdmin != null;
	}
	public boolean isDestinatedToClientInstallment() {
		return this.getDestinationParty() != null ? this.getDestinationParty().is(DN00InteropMessageParty.CLIENT_INSTALLMENT)
										  		  : false;
	}
////////// --- ROUTE DATA
    public DN00InteropContext addRouteItem(final DN00IteropRouteDataItem item) {
    	if (this.messageData == null) throw new IllegalStateException("messageData is null, cannot add route item");
    	if (this.messageData.interopRouteData() == null) throw new IllegalStateException("messageData.interopRouteData is null, cannot add route item");
		this.messageData.interopRouteData().add(item);
		return this;
	}
    public boolean containsRouteItem(final DN00InteropComponent componentId) {
		return this.messageData != null ? this.messageData.containsRouteItem(componentId)
								   	    : false;
    }
    public Stream<DN00IteropRouteDataItem> getRouteDataStreamSortedByTimeStampOldestFirst() {
    	return this.messageData != null ? this.messageData.getRouteDataStreamSortedByTimeStampOldestFirst()
							   			: null;
    }
    public Collection<DN00IteropRouteDataItem> getRouteDataSortedByTimeStamp() {
    	return this.messageData != null ? this.messageData.getRouteDataSortedByTimeStamp()
							   			: null;
    }
    public DN00IteropRouteDataItem getOriginRouteItem() {
    	return this.messageData != null ? this.messageData.getOriginRouteItem()
							   			: null;
	}
    public DN00IteropRouteDataItem getLastRouteItem() {
    	return this.messageData != null ? this.messageData.getLastRouteItem()
							   			: null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	DEBUG
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		if (this.isFromClientInstallment()) {
			return Strings.customized("""
								  	  message: {}
								  	  flow: direction={}
								  	  from clientInstallment: oid={}
								  	  """,
								  	  this.messageData != null ? this.messageData.debugInfo() : null,
								  	  this.originClientInstallmentOid);
		} else if (this.isFromAdministration()) {
			return Strings.customized("""
								  	  message: {}
								  	  flow: direction={}
								  	  from administration: {}
								  	  """,
								  	  this.messageData != null ? this.messageData.debugInfo() : null,
								  	  this.originAdmin);
		} else if (this.isFromClientInstallment()) {
			return Strings.customized("""
									  message: {}
									  flow: direction={}
									  from clientInstallment: oid={}
									  """,
									  this.messageData != null ? this.messageData.debugInfo() : null,
									  this.originClientInstallmentOid);
		} else {
			return Strings.customized("""
									  message: {}
									  flow: direction={}
									  from DENA-CORE
									  """,
									  this.messageData != null ? this.messageData.debugInfo() : null);
		}
	}
}
