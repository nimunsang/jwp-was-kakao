package http;

import http.cookie.HttpCookie;

import java.util.HashMap;
import java.util.Map;

public class RequestHeader {

    public static final String JSESSIONID = "JSESSIONID";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String COOKIE = "Cookie";
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

    public String getJsessionId() {
        if (map.containsKey(COOKIE)) {
            String[] cookies = map.get(COOKIE).split("; ");
            for (String cookie : cookies) {
                if (cookie.contains(JSESSIONID)) {
                    return cookie.split("=")[1];
                }
            }
        }
        return null;
    }

    public HttpCookie getCookie() {
        return new HttpCookie(map.get(COOKIE));
    }
}
