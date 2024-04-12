package http;

public class Body {

    private final byte[] body;

    public Body(byte[] body) {
        this.body = body;
    }

    public Body(String body) {
        this.body = body.getBytes();
    }

    public byte[] getBodyAsBytes() {
        return body;
    }

    public int getLength() {
        return body.length;
    }

    public String getBodyAsString() {
        return new String(body);
    }
}
