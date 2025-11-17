package dev.jonkursani.thymeleafexample;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller // ki mi menaxhu web requestat
@RequestMapping("/") // rruga apo path (route) se qysh vijm deri te kontrolleri
public class HomeController {
    // per mi largu hapesirat e panevojshme nese useri na dergon ne input '      '
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        binder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }

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

    @GetMapping("/process-form")
    public String processMyForm(@RequestParam("myName") String emriIm,
                                @RequestParam("mySurname") String mbiemriIm,
                                @RequestParam("myAge") int moshaIme,
                                Model model) {
        model.addAttribute("emriIm", emriIm);
        model.addAttribute("mbiemriIm", mbiemriIm);
        model.addAttribute("moshaIme", moshaIme);
        return "submitted-form";
    }

    @GetMapping("/user-form") // shfaq formen prandaj duhet me @GetMapping
    public String myFormPost(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/process-user-data")
    public String processUserData(@Valid @ModelAttribute("user") User myUser, BindingResult result) {
        // @ModelAttribute("user") -> e lexon te dhenat nga forma dhe i vendosni ne objektin User
        // dhe i ben te qasshme te dhenat ne HTML pa pas nevoje model.addAttribute()
        // @Valid -> e validon te dhenat nga forma
        // BindingResult -> e lexon te dhenat nga forma dhe i permban errorat
        System.out.println(myUser);

        if (result.hasErrors()) {
            return "user-form";
        } else {
            return "submitted-user";
        }
    }

    @GetMapping("/student-form")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    final Set<Student> students = new HashSet<>();
    @PostMapping("/process-student-data")
    public String processStudentData(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student-form";
        } else {
            students.add(student);
            return "redirect:/submitted-students";
        }
    }

    @GetMapping("/submitted-students")
    public String submittedStudents(Model model) {
        model.addAttribute("studentsList", students);
        return "submitted-students";
    }
}