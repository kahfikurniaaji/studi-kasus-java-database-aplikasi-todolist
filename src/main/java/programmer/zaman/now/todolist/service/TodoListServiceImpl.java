package programmer.zaman.now.todolist.service;

import programmer.zaman.now.todolist.entity.TodoList;
import programmer.zaman.now.todolist.repository.TodoListRepository;

import java.util.List;

public class TodoListServiceImpl implements TodoListService {

    private TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public List<TodoList> getAllTodolist() {
        return todoListRepository.findAll();
    }

    @Override
    public void addTodoList(TodoList todo) {
//        Todolist todolist = new Todolist(todo);
//        todoListRepository.add(todolist);
        System.out.println("SUKSES MENAMBAH TODO : " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
//        boolean success = todoListRepository.remove(number);
//        if (success) {
//            System.out.println("SUKSES MENGHAPUS TODO : " + number);
//        } else {
//            System.out.println("GAGAL MENHAPUS TODO : " + number);
//        }
    }
}
