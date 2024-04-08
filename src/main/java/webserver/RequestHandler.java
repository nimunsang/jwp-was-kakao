package webserver;

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

            while (line != null && !line.equals("")) {
                logger.debug("header : {}", line);
                line = bufferedReader.readLine();
            }

            if (httpMethod.equals(HttpMethod.GET)) {
                DataOutputStream dos = new DataOutputStream(out);

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

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
