package dev.jonkursani.spingjpa;

import dev.jonkursani.spingjpa.entities.Student;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// @Component
@Repository
public class StudentDAOImpl implements StudentDAO {
    private final EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student); // insert into students values (student.getId(), student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
