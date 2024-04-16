package utils;

import http.*;

public class HttpResponseBuilder {

    private ResponseStartLine startLine;
    private ResponseHeader responseHeader;
    private Body body;

    private HttpResponseBuilder(ResponseStartLine startLine, ResponseHeader responseHeader, Body body) {
        this.startLine = startLine;
        this.responseHeader = responseHeader;
        this.body = body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private HttpVersion httpVersion;
        private HttpStatus httpStatus;
        private ResponseHeader responseHeader;
        private Body body;

        private Builder() {
        }

        public Builder httpVersion(HttpVersion httpVersion) {
            this.httpVersion = httpVersion;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder header(ResponseHeader responseHeader) {
            this.responseHeader = responseHeader;
            return this;
        }

        public Builder body(Body body) {
            this.body = body;
            return this;
        }

        public HttpResponse build() {
            ResponseStartLine startLine = new ResponseStartLine(httpVersion, httpStatus);
            return new HttpResponse(startLine, responseHeader, body);
        }
    }
}
