package utils;

public class HttpHeaderUtils {
    public static HeaderType parseType(String header) {
        String[] s = header.split(" ");
        return HeaderType.of(s[0]);
    }
}
