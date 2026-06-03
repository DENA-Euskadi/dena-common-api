package dena.common.utils;

import java.util.Map;

@Deprecated
public class DN00HttpUtils {

    /**
     * Encodes parameters as a URL-encoded string.
     *
     * @param params The map of parameters to encode.
     * @return The encoded parameters as a string.
     */
    public static String encodeParams(final Map<String, String> params) {
        StringBuilder encodedParams = new StringBuilder();
        params.entrySet().stream().forEach(entry -> {
            if (encodedParams.length() > 0) {
                encodedParams.append("&");
            }
            encodedParams.append(entry.getKey()).append("=").append(entry.getValue());
        });
        return encodedParams.toString();
    }
}
