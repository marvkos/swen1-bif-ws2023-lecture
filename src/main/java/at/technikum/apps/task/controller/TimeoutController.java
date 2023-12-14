package at.technikum.apps.task.controller;

import at.technikum.server.http.Request;
import at.technikum.server.http.Response;

public class TimeoutController extends Controller {

    @Override
    public boolean supports(String route) {
        return route.startsWith("/timeout");
    }

    @Override
    public Response handle(Request request) {

        boolean wait = true;
        while (wait) {

        }

        return null;
    }
}
