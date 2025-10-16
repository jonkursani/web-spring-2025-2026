package dev.jonkursani.spingjpa;

import dev.jonkursani.spingjpa.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); // select * from students where id = id;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList(); // select * from students;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student); // update students set first_name = student.getFirstName(), last_name = student.getLastName(), email = student.getEmail() where id = student.getId();
    }

    @Override
    @Transactional
    public void delete(Student student) {
        entityManager.remove(student); // delete from students where id = student.getId();
    }
}
