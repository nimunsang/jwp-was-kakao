package http.redirect;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import http.HttpVersion;
import utils.HttpResponseBuilder;

public class RedirectionHandler {

    public static HttpResponse handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        String endPoint = httpRequest.getEndPoint();
        if (RedirectionMap.has(endPoint)) {
            String to = RedirectionMap.get(endPoint);
            httpResponse.getHeader().setLocation(to);
            return HttpResponseBuilder.builder()
                    .httpVersion(HttpVersion.HTTP_1_1)
                    .httpStatus(HttpStatus.FOUND)
                    .responseHeader(httpResponse.getHeader())
                    .build();
        }
        return httpResponse;
    }
}
