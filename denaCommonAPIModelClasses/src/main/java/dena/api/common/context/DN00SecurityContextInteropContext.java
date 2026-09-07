package dena.api.common.context;

import dena.api.common.interop.context.DN00InteropContext;
import dena.api.common.interop.context.DN00InteropMessageData;
import dena.api.common.model.DN00IsDENAModelObject;
import dena.api.common.model.refs.interopconfig.DN00DataTypeRef;
import dena.api.common.model.refs.orgconfig.DN00OrgAdminRef;
import dena.api.common.model.refs.person.DN00PersonRef;
import lombok.Getter;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.types.JsonString;

/**
 * A COPY of the {@link DN00InteropContext} BUT without ORIGIN data used at the {@link DN00SecurityContext}
 */
public record DN00SecurityContextInteropContext(////////// ----- MESSAGE DATA   
												   /**
												    * Info about the message:
												    * 	- message type
												    * 	- correlation id
												    * 	- flow direction
												    * 	- route data
												    */
												   @MarshallField(as="message")
												   @Getter DN00InteropMessageData messageData,
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
													// json contained user agent info (see r01f.web.useragent.UserAgentParser for creating the json string from the user agent header)
												    // this field is NOT sent by the client, it is set by the server when the request is received
												    // by using r01f.web.useragent.UserAgentParser.userAgentPropertiesAsJson(httpRequest)
												    // the user agent info sent at the "user-agent" http request header MUST contain the app version
												    // (ie: "DENA/1.0.0 (Windows NT 10.0; Win64; x64) ...") so the server can parse it and extract the app version
												    @MarshallField(as="userAgent")
												    @Getter JsonString userAgent) 
   implements DN00IsDENAModelObject{
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00SecurityContextInteropContext(final DN00InteropContext ctx) {
		this(DN00InteropMessageData.unmodifiableCopyOf(ctx.messageData()),	// BEWARE: convert to immutable list copy of the original!!
			 ctx.destinationAdmin(),
			 ctx.subjectPerson(),
			 ctx.aboutDataType(),
			 ctx.userAgent());
	}
	public static DN00SecurityContextInteropContext unmodifiableCopyOf(final DN00SecurityContextInteropContext ctx) {
		return new DN00SecurityContextInteropContext(DN00InteropMessageData.unmodifiableCopyOf(ctx.messageData),	// BEWARE: convert to immutable list copy of the original!!
													 ctx.destinationAdmin(),
													 ctx.subjectPerson(),
													 ctx.aboutDataType(),
													 ctx.userAgent());
	}
	public static DN00SecurityContextInteropContext modifiableCopyOf(final DN00SecurityContextInteropContext ctx) {
		return new DN00SecurityContextInteropContext(DN00InteropMessageData.modifiableCopyOf(ctx.messageData),
													 ctx.destinationAdmin(),
													 ctx.subjectPerson(),
													 ctx.aboutDataType(),
													 ctx.userAgent());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////	
}
