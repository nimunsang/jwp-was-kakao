package utils;

import java.util.Arrays;
import java.util.Objects;

public enum ContentType {
    TEXT_CSS("text/css"),
    TEXT_HTML("text/html");

    private String value;

    ContentType(String value) {
        this.value = value;
    }

    public static ContentType of(String contentTypeString) {
        return Arrays.stream(values())
                .filter(contentType -> Objects.equals(contentType.value, contentTypeString))
                .findFirst()
                .get();
    }

    public String getValue() {
        return value;
    }
}
