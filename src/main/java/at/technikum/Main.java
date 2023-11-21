package at.technikum;

import at.technikum.apps.display.DisplayApp;
import at.technikum.apps.mtcg.MtcgApp;
import at.technikum.apps.task.TaskApp;
import at.technikum.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new TaskApp());
        server.start();
    }
}