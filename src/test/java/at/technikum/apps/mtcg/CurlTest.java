package at.technikum.apps.mtcg;

import at.technikum.server.http.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurlTest {

    MtcgApp mtcgApp = new MtcgApp();


    /*
    Just an idea, maybe an alternative to the curl script
     */
    @Test
    void createUser() {
        Request request = new Request();
        request.setMethod(HttpMethod.POST);
        request.setRoute("http://localhost:10001/users");
        request.setContentType(HttpContentType.APPLICATION_JSON.getMimeType());
        request.setBody("{\"username\":\"kienboec\", \"password\":\"daniel\"}");

        Response response = mtcgApp.handle(request);

        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    }
}
