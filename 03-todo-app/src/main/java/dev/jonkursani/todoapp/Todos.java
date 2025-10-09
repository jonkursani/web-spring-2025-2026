package dev.jonkursani.todoapp;

import java.util.concurrent.atomic.AtomicInteger;

public class Todos {
    // si shembull primary key auto increment masi skemi lidhje me DB
    private static final AtomicInteger id = new AtomicInteger(0);

    public static Todo addTodo(String title) {
        return new Todo(id.getAndIncrement(), title, false);
    }
}
