package http;

import utils.HttpResponseBuilder;

public class HttpResponse {

    private ResponseStartLine startLine;
    private ResponseHeader responseHeader;
    private Body body;

    public HttpResponse(ResponseStartLine startLine, ResponseHeader responseHeader, Body body) {
        this.startLine = startLine;
        this.responseHeader = responseHeader;
        this.body = body;
    }

    public ResponseStartLine getStartLine() {
        return startLine;
    }

    public ResponseHeader getHeader() {
        return responseHeader;
    }

    public Body getBody() {
        return body;
    }

    public static HttpResponse notFound() {
        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }

    public void setCookie() {
        responseHeader.setCookie();
    }
}
