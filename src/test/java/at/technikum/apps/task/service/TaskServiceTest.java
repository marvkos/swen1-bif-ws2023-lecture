package at.technikum.apps.task.service;

import at.technikum.apps.task.entity.Task;
import at.technikum.apps.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Test
    void shouldSetTaskId_whenSaveTask() {
        // Arrange
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);
        Task task = new Task("", "Clean", "Kitchen", false);

        when(taskRepository.save(any())).then(returnsFirstArg());

        // Act
        Task answer = taskService.save(task);

        // Assert
        assertNotEquals("", answer.getId());
        assertEquals("Clean", answer.getName());
        assertEquals("Kitchen", answer.getDescription());
        assertFalse(answer.isDone());
    }

    @Test
    void shouldCallTaskRepository_whenSaveTask() {
        // Arrange
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);
        Task task = new Task("", "Clean", "Kitchen", false);

        // Act
        taskService.save(task);

        // Assert
        verify(taskRepository, times(1)).save(task);
    }
}