package at.technikum.apps.task;

import at.technikum.apps.task.controller.Controller;
import at.technikum.apps.task.controller.TaskController;
import at.technikum.apps.task.controller.TimeoutController;
import at.technikum.apps.task.repository.DatabaseTaskRepository;
import at.technikum.apps.task.repository.TaskRepository;
import at.technikum.apps.task.service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class Injector {

    /*
    A central place to create all classes.
    Dependency Injection via constructor injection.
     */
    public List<Controller> createController() {
        List<Controller> controllerList = new ArrayList<>();

        TaskRepository taskRepository = new DatabaseTaskRepository();
        TaskService taskService = new TaskService(taskRepository);
        controllerList.add(new TaskController(taskService));

        controllerList.add(new TimeoutController());

        return controllerList;
    }
}
