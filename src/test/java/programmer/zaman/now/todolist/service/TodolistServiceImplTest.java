package programmer.zaman.now.todolist.service;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmer.zaman.now.todolist.entity.TodoList;
import programmer.zaman.now.todolist.repository.TodoListRepository;
import programmer.zaman.now.todolist.repository.TodoListRepositoryImpl;
import programmer.zaman.now.todolist.util.DatabaseUtil;

import java.util.List;

public class TodolistServiceImplTest {
    private HikariDataSource dataSource;

    private TodoListRepository todoListRepository;

    private TodoListService todoListService;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDatasource();
        todoListRepository = new TodoListRepositoryImpl(dataSource);
        todoListService = new TodoListServiceImpl(todoListRepository);
    }

    @Test
    void testAddTodolist() {
        TodoList todoList = new TodoList();

        String todo = "Kahfi";
        todoList.setTodo(todo);
        todoList = todoListService.addTodoList();
        Assertions.assertNotNull(todoList.getId());
        Assertions.assertEquals(todo, todoList.getTodo());

        todo = null;
        todoList.setTodo(todo);
        todoList = todoListRepository.save(todoList);
        Assertions.assertNull(todoList.getId());
        Assertions.assertNull(todoList.getTodo());

        todoList = todoListRepository.save(null);
        Assertions.assertNull(todoList.getId());
        Assertions.assertNull(todoList.getTodo());
    }

    @Test
    void testGetAll() {
        List<TodoList> todoList = todoListService.getAllTodolist();

        Assertions.assertTrue(todoList.isEmpty());

//        todoListRepository.save(new TodoList(null, "Kahfi"));
//        todoList = todoListRepository.findAll();
//
//        for (TodoList todos : todoList) {
//            System.out.println(todos.getId() + " : " + todos.getTodo());
//        }
//
//        Assertions.assertTrue(todoList.size() > 0);
//        Assertions.assertFalse(todoList.isEmpty());
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
