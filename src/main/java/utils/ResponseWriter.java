package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;


public class ResponseWriter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseWriter.class);

    private static final String CRLF = "\r\n";
    private final HttpResponse httpResponse;
    private final DataOutputStream dos;

    public ResponseWriter(DataOutputStream dos, HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
        this.dos = dos;
    }

    public void write() throws IOException {
        responseHeader();
        responseBody();
    }

    private void responseHeader() throws IOException {
        String startLine = writeStartLine();
        String header = writeHeader();

        System.out.println(startLine);
        System.out.println(header);

        dos.writeBytes(startLine);
        dos.writeBytes(header);
    }

    private void responseBody() {
        byte[] body = httpResponse.getBody();
//        System.out.println(Arrays.toString(body));

            try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private String writeHeader() throws IOException {
        Header header = httpResponse.getHeader();

        StringBuilder sb = new StringBuilder();
        for (String key : header.getKeys()) {
            sb.append(key)
                    .append(": ")
                    .append(header.get(key))
                    .append(CRLF);
        }
        sb.append(CRLF);
        return sb.toString();
    }

    private String writeStartLine() {
        HttpStatus httpStatus = httpResponse.getStartLine().getHttpStatus();
        HttpVersion httpVersion = httpResponse.getStartLine().getHttpVersion();
        StringBuilder sb = new StringBuilder();
        sb.append(httpVersion.getValue())
                .append(" ")
                .append(httpStatus.getValue())
                .append(" ")
                .append(httpStatus.getReasonPhrase())
                .append(CRLF);

        return sb.toString();
    }
}