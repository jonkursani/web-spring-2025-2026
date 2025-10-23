package dev.jonkursani.thymeleafexample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller // ki mi menaxhu web requestat
@RequestMapping("/") // rruga apo path (route) se qysh vijm deri te kontrolleri
public class HomeController {
    @GetMapping
//    @GetMapping("/home")
    public String home(Model m) {
        // Modeli perdoret per me dergu te dhena ne html template
        m.addAttribute("date", LocalDateTime.now());
        // date -> 2025-10-20T12:58:56.567
        m.addAttribute("isStudent", true);

        List<String> colors = List.of("Red", "Green", "Blue");
        m.addAttribute("colors", colors);

        Map<String, Double> products = Map.of(
                "Laptop", 500.0,
                "Phone", 100.0,
                "Tablet", 200.0
        );
        m.addAttribute("products", products);

        User user = new User("John", 20, "john@example.com");
        m.addAttribute("user", user);

        List<User> users = List.of(
                new User("John", 20, "john@example.com"),
                new User("Jane", 22, "jane@example.com"),
                new User("Bob", 25, "bob@example.com")
        );
        m.addAttribute("users", users);

        return "home"; // "home" na e percakton ni file me emrin home.html (case sensitive)
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        List<String> movies = List.of("Movie 1", "Movie 2", "Movie 3");
        model.addAttribute("movies", movies);
        return "movies"; // movies.html
    }

    @GetMapping("/grades")
    public String grades(Model model) {
        Map<String, Integer> grades = Map.of(
                "Math", 10,
                "Web Development", 8,
                "English", 7
        );

        model.addAttribute("grades", grades);

        return "grades";
    }

    @GetMapping("/student-profile")
    public String studentProfile(Model model) {
        Student student = new Student("Almir", 20, "Cacttus Education");
        model.addAttribute("student", student);
        return "student-profile"; // student-profile.html
    }

    @GetMapping("/students")
    public String studentList(Model model) {
        List<Student> students = List.of(
                new Student("Almir", 20, "Cacttus Education"),
                new Student("John", 22, "Cacttus Education"),
                new Student("Jane", 25, "Cacttus Education")
        );
        model.addAttribute("studentList", students);
        return "students"; // students.html
    }

    @GetMapping("/my-form")
    public String myForm(Model model) {
        return "my-form"; // my-form.html
    }

    @GetMapping("process-form")
    public String processMyForm(@RequestParam("myName") String emriIm, Model model) {
        model.addAttribute("emriIm", emriIm);
        return "submitted-form";
    }
}
