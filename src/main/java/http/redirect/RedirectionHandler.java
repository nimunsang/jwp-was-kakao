package http.redirect;

import http.HttpRequest;
import http.HttpResponse;

public class RedirectionHandler {

    public static HttpResponse handle(HttpRequest httpRequest) {
        String endPoint = httpRequest.getEndPoint();
        if (RedirectionMap.has(endPoint)) {
            String to = RedirectionMap.get(endPoint);
            return Redirector.makeHttpResponse(to);
        }
        return HttpResponse.notFound();
    }
}
