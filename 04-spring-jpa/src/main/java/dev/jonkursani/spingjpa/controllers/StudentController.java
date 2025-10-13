package dev.jonkursani.spingjpa.controllers;

import dev.jonkursani.spingjpa.StudentDAO;
import dev.jonkursani.spingjpa.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    // Dependency Injection
    private final StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("/create")
    public String createStudent() {
        Student student = new Student("John", "Doe", "john.doe@example.com");
        studentDAO.save(student);
        return  "Student created: " + student.toString();
    }
}
