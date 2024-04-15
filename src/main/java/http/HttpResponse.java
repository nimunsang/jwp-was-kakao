package http;

import utils.HttpResponseBuilder;

public class HttpResponse {

    private ResponseStartLine startLine;
    private Header header;
    private Body body;

    public HttpResponse(ResponseStartLine startLine, Header header, Body body) {
        this.startLine = startLine;
        this.header = header;
        this.body = body;
    }

    public ResponseStartLine getStartLine() {
        return startLine;
    }

    public Header getHeader() {
        return header;
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
}
