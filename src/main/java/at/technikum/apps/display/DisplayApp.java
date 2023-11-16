package at.technikum.apps.display;

import at.technikum.server.ServerApplication;
import at.technikum.server.http.HttpContentType;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;

import java.text.SimpleDateFormat;

public class DisplayApp implements ServerApplication {

    @Override
    public Response handle(Request request) {

        System.out.println(request.getMethod() + " " + request.getRoute());

        if (request.getRoute().equals("/hello")) {
            Response response = new Response();
            response.setStatus(HttpStatus.OK);
            response.setContentType(HttpContentType.TEXT_PLAIN);
            response.setBody("World");

            return response;
        }

        if (request.getRoute().equals("/time")) {
            Response response = new Response();
            response.setStatus(HttpStatus.OK);
            response.setContentType(HttpContentType.TEXT_PLAIN);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                    .format(new java.util.Date());
            response.setBody(timeStamp);

            return response;
        }

        Response response = new Response();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setContentType(HttpContentType.TEXT_PLAIN);
        response.setBody("Route " + request.getRoute() + " not found in app!");

        return response;
    }
}
