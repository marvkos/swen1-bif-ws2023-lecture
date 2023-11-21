package at.technikum.server.http;

// THOUGHT: add relevant content types
public enum HttpContentType {
    TEXT_PLAIN("text/plain"),
    APPLICATION_JSON("application/json");

    private final String mimeType;

    HttpContentType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}
