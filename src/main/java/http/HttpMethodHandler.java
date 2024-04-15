package http;

import db.DataBase;
import http.redirect.RedirectionHandler;
import http.redirect.RedirectionMap;
import model.User;
import utils.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpMethodHandler {

    public static HttpResponse doGet(HttpRequest httpRequest) throws IOException, URISyntaxException {
        if (RedirectionMap.has(httpRequest.getEndPoint())) {
            return RedirectionHandler.handle(httpRequest);
        }

        String templateUrl = TemplateUrlBuilder.build(httpRequest.getUrl());
        byte[] byteBody = FileIoUtils.loadFileFromClasspath(templateUrl);
        Body body = new Body(byteBody);

        ContentType contentType = ContentTypeParser.parse(templateUrl);
        Header header = new Header();
        header.setContentType(contentType);
        header.setContentLength(body.getLength());

        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.OK)
                .body(body)
                .header(header)
                .build();
    }

    public static HttpResponse doPost(HttpRequest httpRequest) {
        Body requestBody = httpRequest.getBody();
        if (httpRequest.getEndPoint().equals("/user/create")) {
            QueryParams queryParams = QueryParams.of(requestBody);
            User user = QueryParamMapper.toUser(queryParams);

            DataBase.addUser(user);

            return RedirectionHandler.handle(httpRequest);
        }

        return HttpResponse.notFound();
    }
}
