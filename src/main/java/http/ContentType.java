package http;

import java.util.Arrays;
import java.util.Objects;

public enum ContentType {
    TEXT_CSS("text/css", ".css", "./static"),
    TEXT_HTML("text/html", ".html", "./templates"),
    ICO("image/vnd.microsoft.icon", ".ico", "./templates"),
    JS("text/javascript", ".js", "./static"),
    EOT("application/vnd.ms-fontobject", ".eot", "./static"),
    SVG("image/svg+xml", ".svg", "./static"),
    TTF("font/ttf", ".ttf", "./static"),
    WOFF("font/woff", ".woff", "./static"),
    WOFF2("font/woff2", ".woff2", "./static"),;

    private final String value;
    private final String extension;
    private final String templatePath;

    ContentType(String value, String extension, String templatePath) {
        this.value = value;
        this.extension = extension;
        this.templatePath = templatePath;
    }

    public static ContentType of(String contentTypeString) {
        return Arrays.stream(values())
                .filter(contentType -> Objects.equals(contentType.value, contentTypeString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 Content-Type 입니다"));
    }

    public String getValue() {
        if (this == TEXT_HTML) {
            return value + ";charset=utf-8";
        }
        return value;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public String getExtension() {
        return extension;
    }
}
