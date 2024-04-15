package utils;

public class TemplateUrlBuilder {

    public static String build(String url) {
        return ContentTypeParser.parse(url).getTemplatePath() + url;
    }
}
