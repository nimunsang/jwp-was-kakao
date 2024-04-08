package utils;

public enum HttpMethod {
    GET;

    public static HttpMethod of(String type) {
        // TODO type validation
        return HttpMethod.valueOf(type.toUpperCase());
    }
}
