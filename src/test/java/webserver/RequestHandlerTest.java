package webserver;

import org.junit.jupiter.api.Test;
import utils.FileIoUtils;
import utils.HttpHeaderUtils;
import utils.HttpMethod;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;


class RequestHandlerTest {

    private static final String TEMPLATE_PATH = "./templates";
    private static final String HEADER = "GET /index.html HTTP/1.1";

    @Test
    void 헤더에서_타입_추출하기() {
        HttpMethod httpMethod = HttpHeaderUtils.parseHttpMethod(HEADER);

        assertThat(httpMethod).isEqualTo(HttpMethod.GET);
    }

    @Test
    void 헤더에서_URL_추출하기() {
        String url = HttpHeaderUtils.parseUrl(HEADER);

        assertThat(url).isEqualTo("/index.html");
    }

    @Test
    void 헤더에서_URL_추출해서_해당하는_파일_읽기() throws IOException, URISyntaxException {
        String url = HttpHeaderUtils.parseUrl(HEADER);

        String filePath = TEMPLATE_PATH + url;

        byte[] bytes = FileIoUtils.loadFileFromClasspath(filePath);

        assertThat(filePath).isEqualTo("./templates/index.html");
        assertThat(bytes).isNotEmpty();
    }
}