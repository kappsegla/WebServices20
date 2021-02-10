package x.snowroller.models;

import lombok.*;

//@Data
//public class Todo {
//    public String id;
//    public String title;
//    public boolean completed;
//}

//Immutable  - opposite of mutable
public record Todo(String id,
                   String title,
                   boolean completed) {}