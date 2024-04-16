package http;

import java.util.HashMap;
import java.util.Map;

public class RequestHeader {

    public static final String CONTENT_LENGTH = "Content-Length";

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
}
