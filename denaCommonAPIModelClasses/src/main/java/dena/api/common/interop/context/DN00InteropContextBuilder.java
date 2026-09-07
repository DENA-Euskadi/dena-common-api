package dena.api.common.interop.context;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;

import dena.api.common.model.oids.interop.DN00InteropIDs.DN00InteropComponent;
import dena.api.common.model.oids.interop.DN00InteropOIDs.DN00MessageCorrelationOID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigIDs.DN00OrgAdminID;
import dena.api.common.model.oids.orgconfig.DN00OrgConfigOIDs.DN00OrgAdminOID;
import dena.api.common.model.oids.security.DN00SecurityOIDs.DN00ClientInstallmentOID;
import dena.api.common.model.refs.interopconfig.DN00DataTypeRef;
import dena.api.common.model.refs.orgconfig.DN00OrgAdminRef;
import dena.api.common.model.refs.person.DN00PersonRef;
import r01f.model.builders.BuilderBuildStep;
import r01f.patterns.IsBuilder;
import r01f.patterns.IsBuilderStep;
import r01f.types.JsonString;

/**
 * Builder for {@link DN00InteropContext} instances.
 *
 * <p>This class implements a step-builder pattern using nested step classes so that callers
 * are forced to provide required properties in the correct order. Typical construction
 * follows the chain:
 * 
 * If the message is originated from an administration:
 * <pre>
 * DN00InteropContext ctx = DN00InteropContextBuilder.createMessageOfType(DN00InteropMessageType.CLIENT_RETRIEVE_REQ)
 *													 .withGeneratedCorrelationOID()
 *													 .fromAdministration(DN00OrgAdminRef.from(DN00OrgAdminOID.supply(),DN00OrgAdminID.forId("admin-id")))
 *													 .withSubjectPerson(DN00PersonRef.from(DN00PersonOID.supply(),DN00PersonID.forId("personid")))
 *													 .aboutDataType(DN00DataTypeRef.from(DN00DataTypeOID.supply(),DN00DataTypeID.forId("data-type-id")))
 *													 .build()
 * </pre>
 * if the message is originated from a client installment:
 * <pre>
 * DN00InteropContext ctx = DN00InteropContextBuilder.createMessageOfType(DN00InteropMessageType.CLIENT_RETRIEVE_REQ)
 *													 .withGeneratedCorrelationOID()
 *													 .fromClientInstallment(DN00ClientInstallmentOID.supply())
 *													 .destinatedTo(DN00OrgAdminRef.from(DN00OrgAdminOID.supply(),DN00OrgAdminID.forId("admin-id")))
 *													 .withSubjectPerson(DN00PersonRef.from(DN00PersonOID.supply(),DN00PersonID.forId("personid")))
 *													 .aboutDataType(DN00DataTypeRef.from(DN00DataTypeOID.supply(),DN00DataTypeID.forId("data-type-id")))
 *													 .build();
 * </pre>
 *
 * <p>Use the provided static entry point {@link #createMessageOfType(DN00InteropMessageType)}
 * to start building.
 */
public abstract class DN00InteropContextBuilder 
		   implements IsBuilder {    
////////// ----- MESSAGE 
	public static DN00InteropContextBuilderMessageCorrelationIdStep createMessageOfType(final DN00InteropMessageType messageType) {
		return new DN00InteropContextBuilderMessageCorrelationIdStep(messageType);
	}
////////// ----- CORRELATION ID
	public record DN00InteropContextBuilderMessageCorrelationIdStep(DN00InteropMessageType messageType) {
		public DN00InteropContextBuilderFromStep withCorrelationOID(final DN00MessageCorrelationOID oid) {
			return new DN00InteropContextBuilderFromStep(new DN00InteropMessageData(this.messageType,oid,
																					new LinkedList<DN00IteropRouteDataItem>()));
		}
		public DN00InteropContextBuilderFromStep withGeneratedCorrelationOID() {
			return this.withCorrelationOID(DN00MessageCorrelationOID.supply());
		}
	}
////////// ----- ORIGIN
	public record DN00InteropContextBuilderFromStep(DN00InteropMessageData messageData) 
	   implements IsBuilderStep {
		
		public DN00InteropContextBuilderDestinationAdminStep fromClientInstallment(final DN00ClientInstallmentOID clientInstallmentOid) {
			if (this.messageData.getOriginParty().isNOT(DN00InteropMessageParty.CLIENT_INSTALLMENT)) throw new IllegalStateException("The message type " + this.messageData.getMessageType() + " cannot be originated from a [client installment]");
			
			this.messageData.interopRouteData()
							.add(new DN00IteropRouteDataItem(DN00InteropComponent.CLIENT_INSTALLMENT,Instant.now()));
			return new DN00InteropContextBuilderDestinationAdminStep(messageData,
													   				 clientInstallmentOid,null);
		}
		public DN00InteropContextBuilderAboutPersonStep fromAdmin(final DN00OrgAdminRef adminRef) {
			if (this.messageData.getOriginParty().isNOT(DN00InteropMessageParty.ADMIN)) throw new IllegalStateException("The message type " + this.messageData.getMessageType() + " cannot be originated from an [admin]");
			
			this.messageData.interopRouteData()
							.add(new DN00IteropRouteDataItem(DN00InteropComponent.ADMIN,Instant.now()));
			return new DN00InteropContextBuilderAboutPersonStep(messageData,
													   			null,adminRef,
													   			null);			// not destinated to any admin (comes from an admin)
		}
		public DN00InteropContextBuilderAboutPersonStep fromAdmin(final DN00OrgAdminID adminId) {
			return this.fromAdmin(DN00OrgAdminRef.from(adminId));
		}
		public DN00InteropContextBuilderAboutPersonStep fromAdmin(final DN00OrgAdminOID adminOid) {
			return this.fromAdmin(DN00OrgAdminRef.from(adminOid));
		}
		public DN00InteropContextBuilderDestinationAdminStep fromDENACORE() {
			if (this.messageData.getOriginParty().isNOT(DN00InteropMessageParty.DENA_CORE)) throw new IllegalStateException("The message type " + this.messageData.getMessageType() + " cannot be originated from DENA-CORE");
			
			Collection<DN00IteropRouteDataItem> interopRouteData = new LinkedList<>();
			interopRouteData.add(new DN00IteropRouteDataItem(DN00InteropComponent.DENA_CORE,Instant.now()));
			return new DN00InteropContextBuilderDestinationAdminStep(messageData,
													   				 null,null);
		}
		public DN00InteropContextBuilderDestinationAdminStep fromDENAAdminConnector() {
			if (this.messageData.getOriginParty().isNOT(DN00InteropMessageParty.DENA_ADMIN_CONNECTOR)) throw new IllegalStateException("The message type " + this.messageData.getMessageType() + " cannot be originated from an ADMIN CONNECTOR");
			
			this.messageData.interopRouteData()
							.add(new DN00IteropRouteDataItem(DN00InteropComponent.DENA_ADMIN_CONNECTOR,Instant.now()));
			return new DN00InteropContextBuilderDestinationAdminStep(messageData,
													   				 null,null);
		}
	}
////////// --- DESTINATION
	public record DN00InteropContextBuilderDestinationAdminStep(DN00InteropMessageData messageData,
												  				DN00ClientInstallmentOID fromClientInstallmentOid,DN00OrgAdminRef fromAdminRef) 
	   implements IsBuilderStep {
		public DN00InteropContextBuilderAboutPersonStep destinatedTo(final DN00OrgAdminRef destinationAdmin) {
			return new DN00InteropContextBuilderAboutPersonStep(messageData,
															    this.fromClientInstallmentOid,this.fromAdminRef,
															    destinationAdmin);
		}
		public DN00InteropContextBuilderAboutPersonStep destinatedToCORE() {
			return new DN00InteropContextBuilderAboutPersonStep(messageData,
															    this.fromClientInstallmentOid,this.fromAdminRef,
															    null);
		}
		public DN00InteropContextBuilderAboutPersonStep destinatedToClientInstallment() {
			return new DN00InteropContextBuilderAboutPersonStep(messageData,
															    this.fromClientInstallmentOid,this.fromAdminRef,
															    null);
		}
	}
////////// --- ABOUT PERSON
	public record DN00InteropContextBuilderAboutPersonStep(DN00InteropMessageData messageData,
												  		   DN00ClientInstallmentOID fromClientInstallmentOid,DN00OrgAdminRef fromAdminRef,
												  		   DN00OrgAdminRef destinationAdmin) 
	   implements IsBuilderStep { 
		public DN00InteropContextBuilderAboutDataTypeStep withSubjectPerson(final DN00PersonRef subjectPerson) {
			return new DN00InteropContextBuilderAboutDataTypeStep(messageData,
																  this.fromClientInstallmentOid,this.fromAdminRef,
																  this.destinationAdmin,
																  subjectPerson);
		}
	}
	public record DN00InteropContextBuilderAboutDataTypeStep(DN00InteropMessageData messageData,
												  		   	 DN00ClientInstallmentOID fromClientInstallmentOid,DN00OrgAdminRef fromAdminRef,
												  		     DN00OrgAdminRef destinationAdmin,
												  		     DN00PersonRef subjectPerson) 
	   implements IsBuilderStep { 
		public DN00InteropContextBuilderUserAgentStep aboutDataType(final DN00DataTypeRef aboutDataType) {
			return new DN00InteropContextBuilderUserAgentStep(messageData,
															  this.fromClientInstallmentOid,this.fromAdminRef,
															  this.destinationAdmin,
															  this.subjectPerson,
															  aboutDataType);
		}
	}
	public record DN00InteropContextBuilderUserAgentStep(DN00InteropMessageData messageData,
												  	   	 DN00ClientInstallmentOID fromClientInstallmentOid,DN00OrgAdminRef fromAdminRef,
												  	     DN00OrgAdminRef destinationAdmin,
												  	     DN00PersonRef subjectPerson,
												  	     DN00DataTypeRef aboutDataType)
	   implements IsBuilderStep { 
		public BuilderBuildStep<DN00InteropContext> usingUserAgent(final JsonString userAgent) {
			return new  BuilderBuildStep<>(new DN00InteropContext(messageData,
																  this.fromClientInstallmentOid,this.fromAdminRef,
																  this.destinationAdmin,
																  this.subjectPerson,
																  this.aboutDataType,
																  userAgent));
		}
		public DN00InteropContext build() {
			return this.usingUserAgent(null)
					   .build();
			
		}
	}
}