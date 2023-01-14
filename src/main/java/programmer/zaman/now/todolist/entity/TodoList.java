package programmer.zaman.now.todolist.entity;

public class TodoList {
    private Integer id;

    private String todo;

    public TodoList() {
    }

    public TodoList(Integer id, String todo) {
        this.id = id;
        this.todo = todo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
