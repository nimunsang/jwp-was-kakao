package http.redirect;

import http.*;
import utils.HttpResponseBuilder;

public class Redirector {

    public static HttpResponse makeHttpResponse(String to) {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setLocation(to);
        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.FOUND)
                .responseHeader(responseHeader)
                .build();
    }
}
