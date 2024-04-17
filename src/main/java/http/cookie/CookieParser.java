package http.cookie;

import java.util.HashMap;
import java.util.Map;

public class CookieParser {

    private CookieParser() {
    }

    public static final int KEY_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public static Map<String, String> parse(String cookieString) {
        if (cookieString == null || cookieString.isEmpty()) {
            return Map.of();
        }

        Map<String, String> map = new HashMap<>();
        for (String s : cookieString.split("; ")) {
            String[] split = s.split("=");
            map.put(split[KEY_INDEX], split[VALUE_INDEX]);
        }

        return map;
    }
}
