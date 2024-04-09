package utils;

public class UrlParser {
    public static String parseToQueryString(String url) {
        String[] queryStrings = url.split("\\?");
        return queryStrings[1];
    }
}
