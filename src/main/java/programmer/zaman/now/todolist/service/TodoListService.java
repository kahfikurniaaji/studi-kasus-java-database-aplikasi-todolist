package programmer.zaman.now.todolist.service;

import programmer.zaman.now.todolist.entity.TodoList;

import java.util.List;

public interface TodoListService {

    List<TodoList> getAllTodolist();

    void addTodoList(TodoList todo);

    void removeTodoList(Integer number);

}
