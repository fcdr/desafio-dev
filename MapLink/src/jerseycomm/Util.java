package jerseycomm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Util {
    private static String body = "";

    private static final String appCode = "&applicationCode=desafiodev";
    private static final byte[] parsedKey = { -49, 75, -26, -53, 12, -23, 108, 36, -117, 116, -106, 37, -26, 101, 36, -26, 109, -93, 52, 108, -83, 52, 107, 122, 52, -101, -70, 52, 101 };

    public static String parseToURL(String src) {
        // TODO parse other cases, like accented letters for example
        if (src != null) return src.replaceAll(" ", "%20");
        return "";
    }

    public static String signIt(String inputUrl) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
            URISyntaxException {
        // Convert the string to a URL so we can parse it
        URL url = new URL(inputUrl + appCode);

        String request = sign(url.getPath(), url.getQuery(), body);

        return url.getProtocol() + "://" + url.getHost() + request;
    }

    public static String sign(String path, String query, String body) throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, URISyntaxException {

        // Retrieve the proper URL components to sign
        String resource = path + '?' + query + body;

        // Get an HMAC-SHA1 signing key from the raw key bytes
        SecretKeySpec sha1Key = new SecretKeySpec(parsedKey, "HmacSHA1");

        // Get an HMAC-SHA1 Mac instance and initialize it with the HMAC-SHA1 key
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(sha1Key);

        // compute the binary signature for the request
        byte[] sigBytes = mac.doFinal(resource.getBytes());

        // base 64 encode the binary signature
        // Base64 is JDK 1.8 only - older versions may need to use Apache Commons or similar.
        String signature = Base64.getEncoder().encodeToString(sigBytes);

        // convert the signature to 'web safe' base 64
        signature = signature.replace('+', '-');
        signature = signature.replace('/', '_');

        return resource + "&signature=" + signature;
    }
}
