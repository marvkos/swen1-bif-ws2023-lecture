package at.technikum.apps.task.controller;

import at.technikum.apps.task.entity.Task;
import at.technikum.apps.task.repository.DatabaseTaskRepository;
import at.technikum.apps.task.service.TaskService;
import at.technikum.server.http.HttpContentType;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TaskController extends Controller {

    private final TaskService taskService;

    public TaskController() {
        this.taskService = new TaskService(new DatabaseTaskRepository());
    }

    @Override
    public boolean supports(String route) {
        return route.startsWith("/tasks");
    }

    @Override
    public Response handle(Request request) {

        if (request.getRoute().equals("/tasks")) {
            switch (request.getMethod()) {
                case "GET": return readAll(request);
                case "POST": return create(request);
            }

            // THOUGHT: better 405
            return status(HttpStatus.BAD_REQUEST);
        }

        // get id e.g. from /tasks/1
        String[] routeParts = request.getRoute().split("/");
        int taskId = Integer.parseInt(routeParts[2]);

        switch (request.getMethod()) {
            case "GET": return read(taskId, request);
            case "PUT": return update(taskId, request);
            case "DELETE": return delete(taskId, request);
        }

        // THOUGHT: better 405
        return status(HttpStatus.BAD_REQUEST);
    }

    public Response create(Request request) {

        ObjectMapper objectMapper = new ObjectMapper();
        Task task = null;
        try {
            task = objectMapper.readValue(request.getBody(), Task.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // task = toObject(request.getBody(), Task.class);

        task = taskService.save(task);

        String taskJson = null;
        try {
            taskJson = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Response response = new Response();
        // THOUGHT: better status 201 Created
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentType.APPLICATION_JSON);
        response.setBody(taskJson);

        return response;

        // return json(task);
    }

    public Response readAll(Request request) {
        List<Task> tasks = taskService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String tasksJson = null;
        try {
            tasksJson = objectMapper.writeValueAsString(tasks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        // Object to JSON coming soon

        Response response = new Response();
        // THOUGHT: better status 201 Created
        response.setStatus(HttpStatus.OK);
        response.setContentType(HttpContentType.APPLICATION_JSON);
        response.setBody(tasksJson);

        return response;
    }

    public Response read(int id, Request request) {
        return null;
    }

    public Response update(int id, Request request) {
        return null;
    }

    public Response delete(int id, Request request) {
        return null;
    }
}
