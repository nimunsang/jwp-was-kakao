package http;

import http.cookie.HttpCookie;

public class HttpResponse {

    private ResponseStartLine startLine;
    private ResponseHeader responseHeader;
    private Body body;

    public HttpResponse() {
        this.startLine = new ResponseStartLine(HttpVersion.HTTP_1_1, HttpStatus.NOT_FOUND);
        this.responseHeader = new ResponseHeader();
        this.body = new Body("");
    }

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

    public HttpCookie getCookie() {
        String cookieString = responseHeader.getCookieString();
        return new HttpCookie(cookieString);
    }

    public void setCookie(HttpCookie cookie) {
        responseHeader.setCookie(cookie);
    }
}
