package http;

import http.cookie.HttpCookie;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

public class HttpRequestHandler {

    private final HttpRequest httpRequest;
    private HttpResponse httpResponse;

    public HttpRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.httpResponse = new HttpResponse();
    }

    public HttpResponse handle() throws IOException, URISyntaxException {
        HttpMethod httpMethod = httpRequest.getHttpMethod();

        setJsessionId(httpRequest.getCookie());

        if (httpMethod.equals(HttpMethod.GET)) {
            httpResponse = HttpMethodHandler.doGet(httpRequest, httpResponse);
        }

        if (httpMethod.equals(HttpMethod.POST)) {
            httpResponse = HttpMethodHandler.doPost(httpRequest, httpResponse);
        }
        return httpResponse;
    }

    private void setJsessionId(HttpCookie cookie) {
        if (!cookie.hasJsessionId()) {
            cookie.setCookie("JSESSIONID", UUID.randomUUID().toString());
        }
        httpResponse.setCookie(cookie);
    }
}
