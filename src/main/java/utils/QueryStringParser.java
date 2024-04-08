package utils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryStringParser {

    public static Map<String, String> parse(String url) {
        String[] queryString = url.split("\\?");
        return parseStringToMap(queryString[1]);
    }

    private static Map<String, String> parseStringToMap(String queryString) {
        String[] data = queryString.split("&");
        return Arrays.stream(data)
                .map(it -> it.split("="))
                .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
    }
}
