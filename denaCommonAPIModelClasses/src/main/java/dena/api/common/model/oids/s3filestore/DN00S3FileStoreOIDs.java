package dena.api.common.model.oids.s3filestore;

import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAObjectOID;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import r01f.guids.JavaOIDDispenser;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00S3FileStoreOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsS3FileStoreObjectOID
		     extends DN00IsDENAObjectOID {
		// just extend
	}
	public interface DN00IsS3FileStorePersistableObjectOID
			 extends DN00IsDENAPersistableObjectOID,
			         DN00IsS3FileStoreObjectOID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	S3 File Store Item OID
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="s3FileStoreItemOid")
	public record DN00S3FileStoreItemOID(@Getter String id)
	   implements DN00IsS3FileStorePersistableObjectOID {

        public static DN00S3FileStoreItemOID forId(final String id) {
            return new DN00S3FileStoreItemOID(id);
        }
        public static DN00S3FileStoreItemOID valueOf(final String str) {
            return new DN00S3FileStoreItemOID(str);
        }
        public static DN00S3FileStoreItemOID supply() {
     		return DN00S3FileStoreItemOID.forId(JavaOIDDispenser.generateGUID());
     	}
        @Override
		public String toString() {
			return this.id;
		}
	}
}
