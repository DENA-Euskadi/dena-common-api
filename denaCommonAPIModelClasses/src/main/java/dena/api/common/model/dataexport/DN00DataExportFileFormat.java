package dena.api.common.model.dataexport;

import java.util.Collection;
import java.util.EnumSet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.enums.EnumExtended;

@Accessors(prefix="_")
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public enum DN00DataExportFileFormat 
 implements EnumExtended<DN00DataExportFileFormat> {	
	SQLITE("sqlitedb"),
	CSV("csv"),
	ZIP_OF_JSON("zip"),
	PARQUET("parquet");
	
	@Getter private final String _extension;
	
	public static Collection<DN00DataExportFileFormat> allFormats() {
		return EnumSet.allOf(DN00DataExportFileFormat.class);
	}
	
}
