package utils;

public class ResponseStartLine {
    private final HttpVersion httpVersion;
    private final HttpStatus httpStatus;

    public ResponseStartLine(HttpVersion httpVersion, HttpStatus httpStatus) {
        this.httpVersion = httpVersion;
        this.httpStatus = httpStatus;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
