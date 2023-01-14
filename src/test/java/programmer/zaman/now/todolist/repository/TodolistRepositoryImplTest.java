package programmer.zaman.now.todolist.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmer.zaman.now.todolist.entity.TodoList;
import programmer.zaman.now.todolist.util.DatabaseUtil;

import java.util.List;

public class TodolistRepositoryImplTest {
    private HikariDataSource dataSource;

    private TodoListRepository todoListRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDatasource();
        todoListRepository = new TodoListRepositoryImpl(dataSource);
    }

    @Test
    void testSave() {
        TodoList todoList = new TodoList();

        String todo = "Widiana Pratiwi";
        todoList.setTodo(todo);
        todoList = todoListRepository.save(todoList);
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
    void testIsExist() {
        int id = 1;
        Boolean isExist = todoListRepository.isExist(id);
        Assertions.assertEquals(true, isExist);

        id = 100;
        isExist = todoListRepository.isExist(id);
        Assertions.assertEquals(false, isExist);
    }

    @Test
    void testFindAll() {
        List<TodoList> todoList = todoListRepository.findAll();

        Assertions.assertTrue(todoList.isEmpty());

        todoListRepository.save(new TodoList(null, "Kahfi"));
        todoList = todoListRepository.findAll();

        for (TodoList todos : todoList) {
            System.out.println(todos.getId() + " : " + todos.getTodo());
        }

        Assertions.assertTrue(todoList.size() > 0);
        Assertions.assertFalse(todoList.isEmpty());
    }

    @Test
    void testFindById() {
        TodoList todoList = todoListRepository.findById(1);
        Assertions.assertEquals(1, todoList.getId());
        Assertions.assertEquals("Kurnia", todoList.getTodo());

        todoList = todoListRepository.findById(100);
        Assertions.assertNull(todoList.getId());
        Assertions.assertNull(todoList.getTodo());

        todoList = todoListRepository.findById(null);
        Assertions.assertNull(todoList.getId());
        Assertions.assertNull(todoList.getTodo());
    }

    @Test
    void testDelete() {
        TodoList todoList = todoListRepository.delete(13);
        Assertions.assertEquals(13, todoList.getId());
        Assertions.assertEquals("Kahfi2", todoList.getTodo());
        todoList = todoListRepository.delete(13);
        Assertions.assertNull(todoList);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
