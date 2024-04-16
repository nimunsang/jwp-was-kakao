package http;

public class HttpRequest {
    private RequestStartLine requestStartLine;
    private RequestHeader requestHeader;
    private Body body;

    public HttpRequest(RequestStartLine requestStartLine, RequestHeader requestHeader, Body body) {
        this.requestStartLine = requestStartLine;
        this.requestHeader = requestHeader;
        this.body = body;
    }

    public HttpMethod getHttpMethod() {
        return requestStartLine.getHttpMethod();
    }

    public String getUrl() {
        return requestStartLine.getUrl();
    }

    public String getEndPoint() {
        return requestStartLine.getEndPoint();
    }

    public Body getBody() {
        return body;
    }

    public boolean hasJsessionId() {
        return requestHeader.hasJsessionId();
    }
}
