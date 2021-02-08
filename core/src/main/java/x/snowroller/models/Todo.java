package x.snowroller.models;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class Todo{
    public String id;
    public String title;
    public boolean completed;

    public Todo(String id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}