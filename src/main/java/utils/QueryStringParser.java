package utils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryStringParser {

    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public static Map<String, String> parseToMap(String queryString) {
        String[] data = queryString.split("&");
        return Arrays.stream(data)
                .map(it -> it.split("="))
                .collect(Collectors.toMap(strings -> strings[KEY_INDEX], strings -> strings[VALUE_INDEX]));
    }
}
