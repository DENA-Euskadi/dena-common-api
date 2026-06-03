package dena.api.common.model.oids.person;


import java.util.function.Function;

import dena.api.common.model.oids.person.DN00PersonIDs.DN00IsPersonPersistableObjectID;
import dena.api.common.model.oids.person.DN00PersonOIDs.DN00PersonOID;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.IsPersonID;
import r01f.types.contact.NIFValidator;
import r01f.types.contact.PersonID;

/**
 * Models a person identity card number (spanish dni or social security number)
 */
@MarshallType(as="personId")
@Immutable
public record DN00PersonID(String id)
   implements DN00IsPersonPersistableObjectID<DN00PersonOID>,
   			  IsPersonID {
	
	public DN00PersonID(final String id,
						final boolean strict) {
		this(IsPersonID.normalize(id));	// normalize!!
		//if (strict && !this.isValid()) throw new IllegalArgumentException(Throwables.message("{} is NOT a valid NIF",this.getId()));
	}
	public static DN00PersonID valueOf(final String s) {
		return new DN00PersonID(s);
	}
	public static DN00PersonID forId(final String id) {
		return new DN00PersonID(id);
	}
	public static DN00PersonID forId(final String id,
								 	 final boolean strict) {
		return new DN00PersonID(id,strict);
	}
	public static DN00PersonID fromSpanishNIF(final String id) {
		return DN00PersonID.fromSpanishNIF(id,true);
	}
	public static DN00PersonID fromSpanishNIF(final String id,
										  	  final boolean normalize) {
		return new DN00PersonID(IsPersonID.normalize(id));	// normalize!!
	}
	public static Function<CharSequence,DN00PersonID> FROM_STRING_FUNC = str -> DN00PersonID.forId(str.toString());
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
/////////////////////////////////////////////////////////////////////////////////////////
//	VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public boolean isValid() {
		return true;
	}
	@Override
	public boolean isValidSpanishNIF() {
		return new NIFValidator()
						.validate(this)
						.isValid();
	}
	public static boolean isValidSpanishNIF(final DN00PersonID personId) {
		return personId.isValidSpanishNIF();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	TRANSFORM
/////////////////////////////////////////////////////////////////////////////////////////
	public static final Function<String,DN00PersonID> SPANISH_NIF_FROM_STRING_TRANSFORM = id -> DN00PersonID.forId(id);
	public static final Function<DN00PersonID,String> SPAHINSH_NIF_TO_STRING_TRANSFORM = nif -> nif.asString();
	
	
/////////////////////////////////////////////////////////////////////////////////////////
//	SOME FAKE SPANISH NIFs FOR TEST PURPOSES
/////////////////////////////////////////////////////////////////////////////////////////
	public static DN00PersonID[] FAKE_SPANISH_NIFS = new DN00PersonID[] {
											  				DN00PersonID.fromSpanishNIF("00000000T"),
											  				DN00PersonID.fromSpanishNIF("00000001R"),
											  				DN00PersonID.fromSpanishNIF("11111111H"),
											  				DN00PersonID.fromSpanishNIF("22222222J"),
											  				DN00PersonID.fromSpanishNIF("33333333P"),
											  				DN00PersonID.fromSpanishNIF("44444444A"),
											  				DN00PersonID.fromSpanishNIF("66666666Q"),
											  				DN00PersonID.fromSpanishNIF("77777777B"),
											  				DN00PersonID.fromSpanishNIF("88888888Y"),
											  				DN00PersonID.fromSpanishNIF("99999999R"),
											  				DN00PersonID.fromSpanishNIF("12312312R"),
											  				DN00PersonID.fromSpanishNIF("10101010K"),
											  				DN00PersonID.fromSpanishNIF("20202020E"),
											  				DN00PersonID.fromSpanishNIF("30303030X"),
											  				DN00PersonID.fromSpanishNIF("40404040H"),
											  				DN00PersonID.fromSpanishNIF("50505050C"),
											  				DN00PersonID.fromSpanishNIF("60606060F"),
											  				DN00PersonID.fromSpanishNIF("70707070G"),
											  				DN00PersonID.fromSpanishNIF("80808080L"),
											  				DN00PersonID.fromSpanishNIF("75610293E"),
											};
	public static DN00PersonID randomFakeSpanishNIF() {
		return FAKE_SPANISH_NIFS[(int)(Math.random() * FAKE_SPANISH_NIFS.length)];
	}
	public static DN00PersonID fakeSpanishNIFAt(final int index) {
		return FAKE_SPANISH_NIFS[index];
	}
}
