package dev.jonkursani.spingjpa.services;

import dev.jonkursani.spingjpa.entities.Student;

import java.util.List;

public interface StudentService {
    void create(Student student);
    Student findById(Integer id);
    List<Student> findAll();
    void update(Student student);
    void delete(Student student);
}
