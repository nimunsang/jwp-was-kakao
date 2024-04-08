package utils;

import java.util.Arrays;
import java.util.Objects;

public enum ContentType {
    TEXT_CSS("text/css"),
    TEXT_HTML("text/html");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public static ContentType of(String contentTypeString) {
        return Arrays.stream(values())
                .filter(contentType -> Objects.equals(contentType.value, contentTypeString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 Content-Type 입니다"));
    }

    public String getValue() {
        return value;
    }
}
