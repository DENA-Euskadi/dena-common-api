package dena.api.common.model.securitycontext.user;


import dena.api.common.model.oids.person.DN00PersonIDs.DN00IsPersonPersistableObjectID;
import dena.api.common.model.oids.person.DN00PersonOIDs.DN00PersonOID;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.securitycontext.SecurityIDS.IsIdentityBrokerObjectID;
import r01f.types.contact.PersonID;


@MarshallType(as="personUserId")
@Immutable
public record DN00DENAPersonUserID(String id)
   implements DN00IsPersonPersistableObjectID<DN00PersonOID>,
              IsIdentityBrokerObjectID {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String asString() {
		return this.id;
	}
	@Override
	public String toString() {
		return this.asString();
	}
	@Override
	public String getId() {
		return this.id;
	}
	public PersonID asPersonID() {
		return new PersonID(this.id);
	}
	
	public static DN00DENAPersonUserID from(final IsIdentityBrokerObjectID id) {	
		return DN00DENAPersonUserID.from(id.getRaw());
	}

	public static DN00DENAPersonUserID from(final String asString) {	
		return new DN00DENAPersonUserID(asString);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public boolean isValid() {
		return true;
	}
	

}
