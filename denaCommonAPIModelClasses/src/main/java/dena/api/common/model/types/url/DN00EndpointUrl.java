package dena.api.common.model.types.url;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.facets.HasID;
import r01f.locale.Language;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.url.HasUrl;
import r01f.types.url.Url;

@MarshallType(as="endpointItem")
@Accessors(prefix="_")
public class DN00EndpointUrl
        implements HasUrl,
                   HasID<DN00EndpointUrlID>{
/////////////////////////////////////////////////////////////////////////////////////////
// FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    @MarshallField(as="url")
    @Getter  @Setter private Url _url;

    @MarshallField(as="id")
    @Getter  @Setter private DN00EndpointUrlID _id;


   @MarshallField(as="lang")
   @Getter  @Setter private Language _language;


/////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    public DN00EndpointUrl() {
    }
    public DN00EndpointUrl(final Url url) {
        _url = url;
    }

}
