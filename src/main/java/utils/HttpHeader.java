package utils;

import java.util.HashMap;
import java.util.Map;

public class HttpHeader {
    private final Map<String, String> map = new HashMap<>();

    public HttpHeader() {
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public int getContentLength() {
        return Integer.parseInt(map.get("Content-Length"));
    }
}
