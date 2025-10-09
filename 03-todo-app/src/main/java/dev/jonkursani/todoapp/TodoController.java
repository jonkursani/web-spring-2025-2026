package dev.jonkursani.todoapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Controller
public class TodoController {
    private Set<Todo> todos = new ConcurrentSkipListSet<>(Comparator.comparingInt(Todo::getId));

    public TodoController() {
        todos.add(Todos.addTodo("Todo 1"));
        todos.add(Todos.addTodo("Todo 2"));
        todos.add(Todos.addTodo("Todo 3"));
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", todos);
        return "todos"; // html file me emrin todos.html
    }

    @PostMapping("/add")
    // @RequestParam("new-todo") == name="new-todo"
    public String add(@RequestParam("new-todo") String newTodo) {
        if (newTodo.isBlank()) {
            return "redirect:/";
        }

        todos.add(Todos.addTodo(newTodo));
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    public  String toggle(@PathVariable int id) {
        Todo todo = todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (todo == null) {
            return "redirect:/";
        }

        todo.setCompleted(!todo.isCompleted());
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        todos.removeIf(t -> t.getId() == id);
        return "redirect:/";
    }





}
