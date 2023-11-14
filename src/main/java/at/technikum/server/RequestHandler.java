package at.technikum.server;

import at.technikum.server.http.HttpContentType;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import at.technikum.server.util.HttpMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler {

    private BufferedReader in;
    private PrintWriter out;

    private final Socket client;

    private final ServerApplication app;

    public RequestHandler(Socket client, ServerApplication app) {
        this.client = client;
        this.app = app;
    }

    public void handle() throws IOException {
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String httpRequest = getHttpStringFromStream(in);

        Request request = HttpMapper.toRequestObject(httpRequest);
        Response response = app.handle(request);

        out = new PrintWriter(client.getOutputStream(), true);
        out.write(HttpMapper.toResponseString(response));

        out.close();
        in.close();
        client.close();
    }

    private String getHttpStringFromStream(BufferedReader in) throws IOException {
        StringBuilder builder = new StringBuilder();

        String inputLine;
        while ((inputLine = in.readLine()) != null && !inputLine.equals("")) {
            builder
                    .append(inputLine)
                    .append(System.lineSeparator());
        }

        return builder.toString();
    }
}
