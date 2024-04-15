package http.redirect;

import java.util.HashMap;
import java.util.Map;

public class RedirectionMap {

    public static final String INDEX_HTML = "/index.html";
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("/", INDEX_HTML);
        map.put("", INDEX_HTML);
        map.put("/user/create", INDEX_HTML);
    }

    public static String get(String key) {
        return map.get(key);
    }

    public static boolean has(String key) {
        return map.containsKey(key);
    }
}
