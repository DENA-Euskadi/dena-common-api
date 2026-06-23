package dena.api.common.model;

import r01f.model.facets.ModelObjectFacet;
import r01f.types.url.UrlTemplate;

public interface DN00HasDENAObjectRESTEndPoint
	     extends ModelObjectFacet {
	/**
	 * A REST endpoint URL template for the REST endpoint of a DENA object
	 * The URL template can be used to generate the actual URL by replacing the placeholders with the specific values.
	 * An example of a URL template could be: "https://api.interop.dena.com/my-dena-obj/{objectOid}"
	 * @return
	 */
	public UrlTemplate getRESTEndPointUrlTemplate();
}
