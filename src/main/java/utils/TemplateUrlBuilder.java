package utils;

public class TemplateUrlBuilder {

    private static final String DEFAULT_URL = "./templates";

    public static String build(String url) {
        if (url.endsWith(".html")) {
            return DEFAULT_URL + url;
        }

        if (url.endsWith(".css")) {
            return "./static" + url;
        }

        return DEFAULT_URL + url;
    }
}
