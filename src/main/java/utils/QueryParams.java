package utils;

import java.util.Map;

public class QueryParams {
    private final Map<String, String> map;

    public QueryParams(Map<String, String> map) {
        this.map = map;
    }

    public static QueryParams of(String url) {
        Map<String, String> data = QueryStringParser.parse(url);
        return new QueryParams(data);
    }

    public String get(String key) {
        return map.get(key);
    }
}
