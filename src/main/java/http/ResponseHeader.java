package http;

import http.cookie.HttpCookie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ResponseHeader {
    public static final String LOCATION = "Location";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String SET_COOKIE = "Set-Cookie";

    private final Map<String, String> map = new HashMap<>();

    public void put(String key, String value) {
        map.put(key, value);
    }

    public String get(String key) {
        return map.get(key);
    }

    public int getContentLength() {
        return Integer.parseInt(map.get(CONTENT_LENGTH));
    }

    public Set<String> getKeys() {
        return map.keySet();
    }

    public void setLocation(String location) {
        map.put(LOCATION, location);
    }

    public void setContentType(ContentType contentType) {
        map.put(CONTENT_TYPE, contentType.getValue());
    }

    public void setContentLength(int length) {
        map.put(CONTENT_LENGTH, String.valueOf(length));
    }

    public void setCookie(HttpCookie cookie) {
        map.put(SET_COOKIE, cookie.toString());
    }
}
