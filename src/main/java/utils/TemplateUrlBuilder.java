package utils;

public class TemplateUrlBuilder {

    private static final String DEFAULT_URL = "./templates";

    public static String build(String url) {
        if (url.endsWith(".html") || url.endsWith(".ico")) {
            return DEFAULT_URL + url;
        }

        return "./static" + url;
    }
}
