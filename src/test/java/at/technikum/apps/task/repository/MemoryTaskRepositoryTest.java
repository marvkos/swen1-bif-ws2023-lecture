package at.technikum.apps.task.repository;

import at.technikum.apps.task.entity.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTaskRepositoryTest {

    @Test
    void whenAddOneTask_ThenFindAllShouldReturnOneMore() {
        // Arrange
        MemoryTaskRepository memoryTaskRepository
                = new MemoryTaskRepository();
        Task task = new Task(
                "1234-1234",
                "Clean",
                "Kitchen",
                false
        );

        // Act
        memoryTaskRepository.save(task);
        List<Task> tasks = memoryTaskRepository.findAll();

        // Assert
        assertEquals(1, tasks.size());
    }

    @Test
    void whenDeleteTask_ThenShouldBeRemovedFromFindAll() {
        // Arrange
        MemoryTaskRepository memoryTaskRepository
                = new MemoryTaskRepository();
        Task task = new Task(
                "1234-1234",
                "Clean",
                "Kitchen",
                false
        );
        memoryTaskRepository.save(task);

        // Act
        memoryTaskRepository.delete(task);
        List<Task> tasks = memoryTaskRepository.findAll();

        // Assert
        assertEquals(0, tasks.size());
    }
}