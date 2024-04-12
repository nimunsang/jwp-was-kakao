package utils;

import http.HttpMethod;
import http.RequestStartLine;

public class HttpHeaderUtils {

    public static final String SPACE_DELIMITER = " ";

    public static RequestStartLine parse(String header) {
        HttpMethod httpMethod = parseHttpMethod(header);
        String url = parseUrl(header);

        return new RequestStartLine(httpMethod, url);
    }

    public static HttpMethod parseHttpMethod(String header) {
        String[] s = header.split(SPACE_DELIMITER);
        return HttpMethod.of(s[0]);
    }

    public static String parseUrl(String header) {
        String[] s = header.split(SPACE_DELIMITER);
        return s[1];
    }
}
