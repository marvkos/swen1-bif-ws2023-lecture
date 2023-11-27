package at.technikum.apps.task.service;

import at.technikum.apps.task.entity.Task;
import at.technikum.apps.task.repository.DatabaseTaskRepository;
import at.technikum.apps.task.repository.MemoryTaskRepository;
import at.technikum.apps.task.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new DatabaseTaskRepository();
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> find(int id) {
        return Optional.empty();
    }

    public Task save(Task task) {
        task.setId(UUID.randomUUID().toString());
        return taskRepository.save(task);
    }

    public Task update(int updateId, Task updatedTask) {
        return null;
    }

    public Task delete(Task task) {
        return null;
    }
}
