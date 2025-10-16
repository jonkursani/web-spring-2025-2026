package dev.jonkursani.spingjpa.controllers;

import dev.jonkursani.spingjpa.StudentDAO;
import dev.jonkursani.spingjpa.entities.Student;
import dev.jonkursani.spingjpa.services.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    // Dependency Injection
    private final StudentDAO studentDAO;
    private final StudentService studentService;

    public StudentController(StudentDAO studentDAO, StudentService studentService) {
        this.studentDAO = studentDAO;
        this.studentService = studentService;
    }

    @GetMapping("/create")
    public String createStudent() {
        Student student = new Student("John", "Doe", "john.doe@example.com");
//        studentDAO.save(student);
        studentService.create(student);
        return  "Student created: " + student.toString();
    }

    // /students/1
    @GetMapping("/{id}")
    public String findStudent(@PathVariable Integer id) {
//        Student studentFromDb = studentDAO.findById(id);
        Student studentFromDb = studentService.findById(id);

        if (studentFromDb == null) {
            return "Student not found";
        }

        return  "Student found: " + studentFromDb.toString();
    }

    // /students
    @GetMapping
    public String findAll() {
//        List<Student> students = studentDAO.findAll();
        List<Student> students = studentService.findAll();

        String st = "";

        for (Student student : students) {
            st += student.toString() + " / ";
        }

        return st;
    }

    @GetMapping("/{id}/update")
    public String updateStudent(@PathVariable Integer id) {
//        Student studentFromDb = studentDAO.findById(id);
        Student studentFromDb = studentService.findById(id);


        if (studentFromDb == null) {
            return  "Student not found";
        }

        studentFromDb.setFirstName("Jane");
        studentFromDb.setLastName("Smith");

//        studentDAO.update(studentFromDb);
        studentService.update(studentFromDb);

        return "Student updated: " + studentFromDb.toString();
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id) {
//        Student studentToDelete = studentDAO.findById(id);
        Student studentToDelete = studentService.findById(id);

        if (studentToDelete == null) {
            return "Student not found";
        }

//        studentDAO.delete(studentToDelete);
        studentService.delete(studentToDelete);
        return "Student deleted: " + studentToDelete.toString();
    }
}
