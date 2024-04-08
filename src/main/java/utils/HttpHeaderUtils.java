package utils;

public class HttpHeaderUtils {
    public static RequestHeader parse(String header) {
        HttpMethod httpMethod = parseHttpMethod(header);
        String url = parseUrl(header);

        return new RequestHeader(httpMethod, url);
    }

    public static HttpMethod parseHttpMethod(String header) {
        String[] s = header.split(" ");
        return HttpMethod.of(s[0]);
    }

    public static String parseUrl(String header) {
        String[] s = header.split(" ");
        return s[1];
    }
}
