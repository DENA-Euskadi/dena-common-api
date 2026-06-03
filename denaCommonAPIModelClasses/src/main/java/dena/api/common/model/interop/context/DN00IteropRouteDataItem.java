package dena.api.common.model.interop.context;

import java.time.Instant;

import dena.api.common.model.DN00IsDENAModelObject;
import dena.api.common.model.interop.oids.DN00InteropIDs.DN00InteropComponentID;
import lombok.experimental.Accessors;
import r01f.debug.Debuggable;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.DateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallDateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.types.Strings;

/**
 * Represents a trace entry indicating that a message has passed through a DENA component.
 * Each time an interoperability message passes through a DENA component, it leaves a trace
 * that can be used for debugging, auditing, and traceability purposes.
 * 
 * Examples of DENA components: mobileApp, webApp, apiGateway, connector, orchestrator
 */
@Accessors(prefix = "_")
@MarshallType(as="interopRouteDataItem")
public record DN00IteropRouteDataItem(/**
									   * Identifier of the DENA component that processed the message.
									   * Examples: "mobileApp", 
									   *           "webApp",
									   *           "apiGateway",
									   *           "connector", 
									   *           "orchestrator"
									   */
									  @MarshallField(as="denaComponentId",
									  				 whenXml=@MarshallFieldAsXml(attr=true))
									  DN00InteropComponentID componentId,
									  /**
								       * Timestamp of the exact moment when the component processed the message.
								       * ISO-8601 UTC format.
								       */
								      @MarshallField(as="timestamp",dateFormat=@MarshallDateFormat(use=DateFormat.ISO8601),
								    			     whenXml=@MarshallFieldAsXml(attr=true))
								      Instant timeStamp)
  implements DN00IsDENAModelObject,
			 Debuggable {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00IteropRouteDataItem(final DN00InteropComponentID componentId) {
		this(componentId, 
			 Instant.now());
	}
	public static DN00IteropRouteDataItem of(final DN00InteropComponentID componentId) {
		return new DN00IteropRouteDataItem(componentId);
	}
	public DN00IteropRouteDataItem at(final Instant timeStamp) {
		return new DN00IteropRouteDataItem(this.componentId,
										   timeStamp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	GETTERS
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00InteropComponentID getComponentId() {
		return this.componentId;
	}
	public Instant getTimeStamp() {
		return this.timeStamp;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DEBUG INFO
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		return Strings.customized("componentId={} at={}",
								  this.componentId,this.timeStamp);
	}
}
