package webserver;

import org.junit.jupiter.api.Test;
import utils.HeaderType;
import utils.HttpHeaderUtils;

import static org.assertj.core.api.Assertions.assertThat;


class RequestHandlerTest {

    @Test
    void 헤더에서_타입_추출하기() {
        String header = "GET /index.html HTTP/1.1";

        HeaderType headerType = HttpHeaderUtils.parseType(header);

        assertThat(headerType).isEqualTo(HeaderType.GET);
    }

    @Test
    void 헤더에서_URL_추출하기() {
        String header = "GET /index.html HTTP/1.1";

        String url = HttpHeaderUtils.parseUrl(header);

        assertThat(url).isEqualTo("/index.html");
    }
}