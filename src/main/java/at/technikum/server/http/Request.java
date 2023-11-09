package at.technikum.server.http;

public class Request {

    // GET, POST, PUT, DELETE
    private String method;

    // /, /home, /package
    private String route;

    // application/json, text/plain
    private String contentType;

    // 0, 17
    private int contentLength;

    // none, "{ "name": "foo" }"
    private String body;

    public void setMethod(HttpMethod httpMethod) {
        this.method = httpMethod.getMethod();
    }
}
