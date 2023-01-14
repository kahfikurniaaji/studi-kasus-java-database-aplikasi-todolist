package programmer.zaman.now.todolist.repository;

import programmer.zaman.now.todolist.entity.TodoList;

import java.util.List;

public interface TodoListRepository {
    TodoList save(TodoList todoList);

    List<TodoList> findAll();

    TodoList findById(Integer id);

    TodoList delete(Integer id);

    Boolean isExist(Integer id);
}
