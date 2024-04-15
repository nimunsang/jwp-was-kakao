package http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Header {
    public static final String LOCATION = "Location";
    public static final String CONTENT_TYPE = "Content-Type";
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
}
