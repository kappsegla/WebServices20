package x.snowroller.models;

public class Todo {
    int id;
    String title;
    boolean completed;

    public Todo(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
