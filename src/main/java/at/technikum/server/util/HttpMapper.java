package at.technikum.server.util;

import at.technikum.server.http.HttpMethod;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// THOUGHT: Maybe divide the HttpMatter into two classes (single responsibility)
// THOUGHT: Dont use static methods (non-static is better for testing)
public class HttpMapper {

    public static Request toRequestObject(String httpRequest) {
        Request request = new Request();

        request.setMethod(getHttpMethod(httpRequest));
        request.setRoute(getRoute(httpRequest));
        request.setHost(getHttpHeader("Host", httpRequest));

        return request;
    }

    public static String toResponseString(Response response) {

        return "HTTP/1.1 " + response.getStatusCode() + " " + response.getStatusMessage() + "\r\n" +
                "Content-Type: " + response.getContentType() + "\r\n" +
                "Content-Length: " + response.getBody().length() + "\r\n" +
                "\r\n" +
                response.getBody();
    }

    // THOUGHT: Maybe some better place for this logic?
    private static HttpMethod getHttpMethod(String httpRequest) {
        String httpMethod = httpRequest.split(" ")[0];

        // THOUGHT: Use constants instead of hardcoded strings
        return switch (httpMethod) {
            case "GET" -> HttpMethod.GET;
            case "POST" -> HttpMethod.POST;
            case "PUT" -> HttpMethod.PUT;
            case "DELETE" -> HttpMethod.DELETE;
            default -> throw new RuntimeException("No HTTP Method");
        };
    }

    private static String getRoute(String httpRequest) {
        return httpRequest.split(" ")[1];
    }

    private static String getHttpHeader(String header, String httpRequest) {
        Pattern regex = Pattern.compile("^" + header + ":\\s(.+)", Pattern.MULTILINE);
        Matcher matcher = regex.matcher(httpRequest);

        if (!matcher.find()) {
            return null;
        }

        return matcher.group(1);
    }
}
