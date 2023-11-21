package at.technikum.apps.task.repository;

import at.technikum.apps.task.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks;

    public MemoryTaskRepository() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Optional<Task> find(int id) {
        return Optional.empty();
    }

    @Override
    public Task save(Task task) {
        return task;
    }

    @Override
    public Task delete(Task task) {
        return task;
    }
}
