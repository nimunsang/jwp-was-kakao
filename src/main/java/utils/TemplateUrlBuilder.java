package utils;

public class TemplateUrlBuilder {

    private static final String DEFAULT_URL = "./templates";

    public static String build(String url) {
        return DEFAULT_URL + url;
    }
}
