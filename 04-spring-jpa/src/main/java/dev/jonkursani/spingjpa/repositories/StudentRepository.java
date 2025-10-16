package dev.jonkursani.spingjpa.repositories;

import dev.jonkursani.spingjpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> { // <Modeli, Tipi i Primary Key>
}
