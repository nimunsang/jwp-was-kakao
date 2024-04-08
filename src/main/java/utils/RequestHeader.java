package utils;

public class RequestHeader {
    private HttpMethod httpMethod;
    private String url;

    public RequestHeader(HttpMethod httpMethod, String url) {
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }
}