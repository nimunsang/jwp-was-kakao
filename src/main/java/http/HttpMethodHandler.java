package http;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import db.DataBase;
import http.cookie.HttpCookie;
import http.redirect.RedirectionHandler;
import http.redirect.RedirectionMap;
import http.redirect.Redirector;
import http.session.Session;
import http.session.SessionManager;
import model.User;
import utils.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpMethodHandler {

    public static HttpResponse doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException, URISyntaxException {
        if (RedirectionMap.has(httpRequest.getEndPoint())) {
            return RedirectionHandler.handle(httpRequest, httpResponse);
        }

        String endPoint = httpRequest.getEndPoint();
        Handlebars handlebars = getHandlebars();
        String jsessionId = httpRequest.getJsessionId();

        if (endPoint.equals("/user/list.html")) {
            if (SessionManager.findSession(jsessionId).isPresent()) {

                Template template = handlebars.compile("user/list");
                final Map<String, Object> model = new HashMap<>();
                model.put("users", DataBase.findAll());
                final String userListPage = template.apply(model);

                return HttpResponseBuilder.builder()
                        .httpVersion(HttpVersion.HTTP_1_1)
                        .httpStatus(HttpStatus.OK)
                        .body(new Body(userListPage))
                        .responseHeader(httpResponse.getHeader())
                        .build();
            }

            return Redirector.makeHttpResponse("/user/login.html");
        }


        if (endPoint.equals("/user/login.html") && SessionManager.findSession(jsessionId).isPresent()) {
            return Redirector.makeHttpResponse("/index.html");
        }

        String templateUrl = TemplateUrlBuilder.build(httpRequest.getUrl());
        byte[] byteBody = FileIoUtils.loadFileFromClasspath(templateUrl);
        Body body = new Body(byteBody);
        ContentType contentType = ContentTypeParser.parse(templateUrl);
        httpResponse.getHeader().setContentType(contentType);
        httpResponse.getHeader().setContentLength(body.getLength());

        return HttpResponseBuilder.builder()
                .httpVersion(HttpVersion.HTTP_1_1)
                .httpStatus(HttpStatus.OK)
                .body(body)
                .responseHeader(httpResponse.getHeader())
                .build();
    }

    public static HttpResponse doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        Body requestBody = httpRequest.getBody();
        String endPoint = httpRequest.getEndPoint();

        if (endPoint.equals("/user/create")) {
            QueryParams queryParams = QueryParams.of(requestBody);
            User user = QueryParamMapper.toUser(queryParams);

            DataBase.addUser(user);

            return RedirectionHandler.handle(httpRequest, httpResponse);
        }

        if (endPoint.equals("/user/login")) {
            QueryParams queryParams = QueryParams.of(requestBody);
            User user = QueryParamMapper.toUser(queryParams);

            if (DataBase.findUserById(user.getUserId()).isPresent()) {
                HttpCookie cookie = httpResponse.getCookie();
                cookie.setCookie("logined", "true");
                httpResponse.setCookie(cookie);
                httpResponse = RedirectionHandler.handle(httpRequest, httpResponse);

                Session session = new Session(cookie.getJsessionid());
                session.setAttribute("user", user);
                SessionManager.add(session);

                return httpResponse;
            }

            return Redirector.makeHttpResponse("/user/login_failed.html");
        }

        return httpResponse;
    }

    private static Handlebars getHandlebars() {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        return new Handlebars(loader);
    }
}
