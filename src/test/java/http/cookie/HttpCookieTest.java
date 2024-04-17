package http.cookie;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpCookieTest {

    @Test
    void 생성() {
        HttpCookie httpCookie = new HttpCookie("JSESSIONID=1234; name=hello");
        assertEquals("1234", httpCookie.get("JSESSIONID"));
        assertEquals("hello", httpCookie.get("name"));
    }

    @Test
    void 쿠키를_문자열로_변환() {
        HttpCookie httpCookie = new HttpCookie("");
        httpCookie.setCookie("JSESSIONID", "1234");
        httpCookie.setCookie("name", "hello");

        String cookieString = httpCookie.toString();
        assertThat(cookieString).contains("JSESSIONID=1234")
                .contains("name=hello");
    }
}