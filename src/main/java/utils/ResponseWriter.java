package utils;

import http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;


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
        dos.writeBytes(startLine);
        dos.writeBytes(header);
    }

    private void responseBody() {
        Body body = httpResponse.getBody();
        try {
            dos.write(body.getBodyAsBytes(), 0, body.getLength());
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
