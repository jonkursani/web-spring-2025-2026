package dev.jonkursani.spingjpa.services.impls;

import dev.jonkursani.spingjpa.entities.Student;
import dev.jonkursani.spingjpa.repositories.StudentRepository;
import dev.jonkursani.spingjpa.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
//        studentRepository.delete(student);
        studentRepository.deleteById(student.getId());
    }
}
