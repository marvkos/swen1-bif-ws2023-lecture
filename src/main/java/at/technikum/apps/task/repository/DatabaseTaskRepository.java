package at.technikum.apps.task.repository;

import at.technikum.apps.task.data.Database;
import at.technikum.apps.task.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseTaskRepository implements TaskRepository {

    private final String FIND_ALL_SQL = "SELECT * FROM task";
    private final String SAVE_SQL = "INSERT INTO task(id, name, description, done) VALUES(?, ?, ?, ?)";

    private final Database database = new Database();

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(FIND_ALL_SQL);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("done")
                );
                tasks.add(task);
            }

            return tasks;
        } catch (SQLException e) {
            return tasks;
        }
    }

    @Override
    public Optional<Task> find(int id) {
        return Optional.empty();
    }

    @Override
    public Task save(Task task) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE_SQL)
        ) {
            pstmt.setString(1, task.getId());
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getDescription());
            pstmt.setBoolean(4, task.isDone());

            pstmt.execute();
        } catch (SQLException e) {
            // THOUGHT: how do i handle exceptions (hint: look at the TaskApp)
        }

        return task;
    }

    @Override
    public Task delete(Task task) {
        return null;
    }
}
