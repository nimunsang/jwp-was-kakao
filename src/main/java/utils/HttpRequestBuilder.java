package utils;

import http.*;
import http.cookie.CookieParser;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestBuilder {

    public static final int KEY_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    private final BufferedReader bufferedReader;
    private RequestStartLine requestStartLine;
    private RequestHeader requestHeader;
    private Body body;

    public HttpRequestBuilder(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public HttpRequest build() throws IOException {
        this.requestStartLine = buildStartLine();
        this.requestHeader = buildRequestHeader();
        this.body = buildBody();
        return new HttpRequest(requestStartLine, requestHeader, body);
    }

    private RequestStartLine buildStartLine() throws IOException {
        String line = bufferedReader.readLine();
        return HttpHeaderUtils.parse(line);
    }

    private RequestHeader buildRequestHeader() throws IOException {
        String line;
        RequestHeader requestHeader = new RequestHeader();
        while (true) {
            line = bufferedReader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            String[] split = line.split(": ");
            requestHeader.put(split[KEY_INDEX], split[VALUE_INDEX]);
        }

        return requestHeader;
    }

    private Body buildBody() throws IOException {
        if (requestStartLine.getHttpMethod().equals(HttpMethod.GET)) {
            return null;
        }

        String data = IOUtils.readData(bufferedReader, requestHeader.getContentLength());
        return new Body(data);
    }
}
