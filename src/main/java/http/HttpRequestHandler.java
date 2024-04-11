package http;

import db.DataBase;
import http.*;
import model.User;
import utils.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpRequestHandler {

    private HttpRequest httpRequest;

    public HttpRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpResponse handle() throws IOException, URISyntaxException {
        HttpMethod httpMethod = httpRequest.getHttpMethod();
        String endPoint = httpRequest.getEndPoint();
        String url = httpRequest.getUrl();
        Header header = new Header();
        byte[] body;

        if (httpMethod.equals(HttpMethod.GET)) {
            if (endPoint.equals("/") || endPoint.isEmpty()) {
                return redirect("/index.html");
            }
            String templateUrl = TemplateUrlBuilder.build(url);
            ContentType contentType = ContentTypeParser.parse(templateUrl);

            body = FileIoUtils.loadFileFromClasspath(templateUrl);

            header.put("Content-Length", String.valueOf(body.length));
            header.put("Content-Type", contentType.getValue() + ";charset=utf-8");

            return HttpResponseBuilder.builder()
                    .httpVersion(HttpVersion.HTTP_1_1)
                    .httpStatus(HttpStatus.OK)
                    .body(body)
                    .header(header)
                    .build();
        }

        if (httpMethod.equals(HttpMethod.POST)) {
            String requestbody = httpRequest.getBody();
            if (endPoint.equals("/user/create")) {
                QueryParams queryParams = QueryParams.of(requestbody);
                User user = QueryParamMapper.toUser(queryParams);

                DataBase.addUser(user);

                return redirect("/index.html");
            }
        }

        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }

    private HttpResponse redirect(String to) {
        Header header = new Header();
        header.put("location", "http://localhost:8080" + to);
        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.FOUND)
                .header(header)
                .build();
    }
}
