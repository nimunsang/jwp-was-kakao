package http.cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpCookie {

    private static final String JSESSIONID = "JSESSIONID";

    private Map<String, String> map = new HashMap<>();

    public HttpCookie(String cookieString) {
        Map<String, String> cookieMap = CookieParser.parse(cookieString);
        map.putAll(cookieMap);
    }

    public String get(String key) {
        return map.get(key);
    }

    public boolean hasJsessionId() {
        return map.containsKey(JSESSIONID);
    }

    public void setCookie() {
        String sessionId = UUID.randomUUID().toString();
        map.put(JSESSIONID, sessionId);
    }

    @Override
    public String toString() {
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((a, b) -> a + "; " + b)
                .orElse("");
    }
}
