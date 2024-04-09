package utils;

public enum HttpMethod {
    GET, POST;

    public static HttpMethod of(String type) {
        // TODO type validation
        return HttpMethod.valueOf(type.toUpperCase());
    }
}
