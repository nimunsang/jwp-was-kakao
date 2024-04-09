package webserver;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = bufferedReader.readLine();

            RequestHeader requestHeader = HttpHeaderUtils.parse(line);

            HttpMethod httpMethod = requestHeader.getHttpMethod();
            String url = requestHeader.getUrl();
            String endPoint = HttpHeaderUtils.parseEndPoint(line);

            HttpHeader httpHeader = new HttpHeader();
            while (true) {
                logger.debug("header : {}", line);

                line = bufferedReader.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] split = line.split(": ");
                httpHeader.put(split[0], split[1]);
            }

            if (httpMethod.equals(HttpMethod.GET)) {
                DataOutputStream dos = new DataOutputStream(out);

                if (endPoint.equals("/user/create")) {
                    QueryParams queryParams = QueryParams.of(url);
                    User user = QueryParamMapper.toUser(queryParams);

                    DataBase.addUser(user);

                    byte[] body = "성공했습니다.".getBytes();
                    response200Header(dos, body.length);
                    responseBody(dos, body);
                    return;
                }

                String templateUrl = TemplateUrlBuilder.build(url);
                ContentType contentType = ContentTypeParser.parse(templateUrl);

                byte[] body = FileIoUtils.loadFileFromClasspath(templateUrl);

                response200Header(dos, body.length, contentType.getValue());
                responseBody(dos, body);

                return;
            }

            if (httpMethod.equals(HttpMethod.POST)) {
                String requestbody = IOUtils.readData(bufferedReader, httpHeader.getContentLength());
                logger.debug("parse body : {} ,", requestbody);

                DataOutputStream dos = new DataOutputStream(out);

                if (endPoint.equals("/user/create")) {
                    QueryParams queryParams = QueryParams.of(requestbody);
                    User user = QueryParamMapper.toUser(queryParams);

                    DataBase.addUser(user);

                    byte[] body = "성공했습니다.".getBytes();
                    response302Header(dos, "http://localhost:8080/index.html");
                    responseBody(dos, body);
                    return;
                }

                String templateUrl = TemplateUrlBuilder.build(url);
                ContentType contentType = ContentTypeParser.parse(templateUrl);

                byte[] body = FileIoUtils.loadFileFromClasspath(templateUrl);

                response200Header(dos, body.length, contentType.getValue());
                responseBody(dos, body);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void response302Header(DataOutputStream dos, String location) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found\r\n");
            dos.writeBytes("Location: " + location + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent, String contentType) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
