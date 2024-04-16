package http;

import db.DataBase;
import http.redirect.RedirectionHandler;
import http.redirect.RedirectionMap;
import http.redirect.Redirector;
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
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setContentType(contentType);
        responseHeader.setContentLength(body.getLength());

        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.OK)
                .body(body)
                .responseHeader(responseHeader)
                .build();
    }

    public static HttpResponse doPost(HttpRequest httpRequest) {
        Body requestBody = httpRequest.getBody();
        String endPoint = httpRequest.getEndPoint();
        if (endPoint.equals("/user/create")) {
            QueryParams queryParams = QueryParams.of(requestBody);
            User user = QueryParamMapper.toUser(queryParams);

            DataBase.addUser(user);

            return RedirectionHandler.handle(httpRequest);
        }

        if (endPoint.equals("/user/login")) {
            QueryParams queryParams = QueryParams.of(requestBody);
            User user = QueryParamMapper.toUser(queryParams);

            if (DataBase.findUserById(user.getUserId()).isPresent()) {
                return RedirectionHandler.handle(httpRequest);
            }

            return Redirector.makeHttpResponse("/user/login_failed.html");
        }

        return HttpResponse.notFound();
    }
}
