package programmer.zaman.now.todolist.repository;

import com.zaxxer.hikari.HikariDataSource;
import programmer.zaman.now.todolist.entity.TodoList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryImpl implements TodoListRepository {
    private HikariDataSource dataSource;

    public TodoListRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TodoList save(TodoList todoList) {

        if (todoList != null) {
            String sql = "INSERT INTO todolist (todo) VALUES (?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                if (todoList.getTodo() == null) {
                    return new TodoList(null, null);
                }

                statement.setString(1, todoList.getTodo());
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    return findById(resultSet.getInt(1));
                }

            } catch (SQLException exception) {
                throw new RuntimeException();
            }
        }

        return new TodoList(null, null);
    }

    @Override
    public List<TodoList> findAll() {

        String sql = "SELECT id, todo FROM todolist";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            List<TodoList> list = new ArrayList<>();

            while (resultSet.next()) {
                TodoList todoList = new TodoList(resultSet.getInt(1), resultSet.getString(2));
                list.add(todoList);
            }

            return list;

        } catch (SQLException exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public TodoList findById(Integer id) {
        if (isExist(id)) {

            String sql = "SELECT id, todo FROM todolist WHERE id = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                TodoList todolist = new TodoList();

                if (resultSet.next()) {
                    todolist.setId(resultSet.getInt(1));
                    todolist.setTodo(resultSet.getString(2));

                    return todolist;
                }

            } catch (SQLException exception) {
                throw new RuntimeException();
            }
        }

        return new TodoList(null, null);
    }

    @Override
    public TodoList delete(Integer id) {
        if (isExist(id)) {
            String sql = "DELETE FROM todolist WHERE id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                TodoList todolist = findById(id);
                statement.executeUpdate();
                return todolist;
            } catch (SQLException exception) {
                throw new RuntimeException();
            }
        }
        return null;
    }

    @Override
    public Boolean isExist(Integer id) {
        if (id != null) {
            String sql = "SELECT  id FROM todolist WHERE id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                return resultSet.next();

            } catch (SQLException exception) {
                throw new RuntimeException();
            }
        }

        return false;
    }
}
