package utils;

public enum HeaderType {
    GET;

    public static HeaderType of(String type) {
        // TODO type validation
        return HeaderType.valueOf(type.toUpperCase());
    }
}
