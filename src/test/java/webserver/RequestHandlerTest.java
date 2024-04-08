package webserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.HeaderType;
import utils.HttpHeaderUtils;


class RequestHandlerTest {

    @Test
    void 헤더에서_타입_추출하기() {
        String header = "GET /index.html HTTP/1.1";

        HeaderType headerType = HttpHeaderUtils.parseType(header);

        Assertions.assertThat(headerType).isEqualTo(HeaderType.GET);
    }
}