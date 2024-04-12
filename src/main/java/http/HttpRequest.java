package http;

public class HttpRequest {
    private RequestStartLine requestStartLine;
    private Header header;
    private Body body;

    public HttpRequest(RequestStartLine requestStartLine, Header header, Body body) {
        this.requestStartLine = requestStartLine;
        this.header = header;
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

    public int getContentLength() {
        return header.getContentLength();
    }

    public Body getBody() {
        return body;
    }
}
