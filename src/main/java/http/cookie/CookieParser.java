package http.cookie;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CookieParser {

    public static final int KEY_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public static Map<String, String> parse(String cookieString) {
        if (cookieString == null || cookieString.isEmpty()) {
            return Map.of();
        }

        String[] cookieParts = cookieString.split("; ");
        return Arrays.stream(cookieParts)
                .map(part -> part.split("="))
                .collect(Collectors.toMap(split -> split[KEY_INDEX], split -> split[VALUE_INDEX]));
    }
}
