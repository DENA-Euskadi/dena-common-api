package dena.api.common.model.oids.s3filestore;

import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAObjectID;
import dena.api.common.model.oids.DN00CommonIDs.DN00IsDENAPersistableObjectID;
import dena.api.common.model.oids.s3filestore.DN00S3FileStoreOIDs.DN00IsS3FileStorePersistableObjectOID;
import lombok.NoArgsConstructor;
import r01f.objectstreamer.annotations.MarshallType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public abstract class DN00S3FileStoreIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE INTERFACES
/////////////////////////////////////////////////////////////////////////////////////////
	public interface DN00IsS3FileStoreObjectID
		     extends DN00IsDENAObjectID {
		// just extend
	}
	public interface DN00IsS3FileStorePersistableObjectID<O extends DN00IsS3FileStorePersistableObjectOID>
			 extends DN00IsDENAPersistableObjectID<O>,
			         DN00IsS3FileStoreObjectID {
		// just a marker interface to be implemented by all OIDs of persistable model objects of the domain
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	S3 File Store Object ID
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="s3BucketId")
	public record DN00S3BucketID(String id)
	   implements DN00IsS3FileStoreObjectID {

        public static DN00S3BucketID forId(final String id) {
            return new DN00S3BucketID(id);
        }
        public static DN00S3BucketID valueOf(final String str) {
            return new DN00S3BucketID(str);
        }
        public static DN00S3BucketID named(final String name) {
			return new DN00S3BucketID(name);
		}
        @Override
		public String toString() {
			return this.asString();
		}
	}
}
