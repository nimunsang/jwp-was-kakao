package http;

import java.util.HashMap;
import java.util.Map;

public class RequestHeader {

    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String COOKIE = "Cookie";
    public static final String JSESSIONID = "JSESSIONID";

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

    public boolean hasJsessionId() {
        if (map.containsKey(COOKIE)) {
            return map.get(COOKIE).contains(JSESSIONID);
        }
        return false;
    }
}
