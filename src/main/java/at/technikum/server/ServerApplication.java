package at.technikum.server;

import at.technikum.server.http.Request;
import at.technikum.server.http.Response;

public interface ServerApplication {

    Response handle(Request request);
}
