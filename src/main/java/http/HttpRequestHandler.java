package http;

import utils.HttpResponseBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpRequestHandler {

    private final HttpRequest httpRequest;

    public HttpRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpResponse handle() throws IOException, URISyntaxException {
        HttpMethod httpMethod = httpRequest.getHttpMethod();
        HttpResponse httpResponse;

        if (httpMethod.equals(HttpMethod.GET)) {
            httpResponse = HttpMethodHandler.doGet(httpRequest);
            if (!httpRequest.hasJsessionId()) {
                httpResponse.setCookie();
            }
            return httpResponse;
        }

        if (httpMethod.equals(HttpMethod.POST)) {
            httpResponse = HttpMethodHandler.doPost(httpRequest);
            if (!httpRequest.hasJsessionId()) {
                httpResponse.setCookie();
            }
            return httpResponse;
        }
        return HttpResponse.notFound();
    }
}
