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

        if (httpMethod.equals(HttpMethod.GET)) {
            return HttpMethodHandler.doGet(httpRequest);
        }

        if (httpMethod.equals(HttpMethod.POST)) {
            return HttpMethodHandler.doPost(httpRequest);
        }

        return HttpResponse.notFound();
    }
}
