package dev.jonkursani.spingjpa.entities;

import jakarta.persistence.*;

@Entity // entitet
@Table(name = "students") // emri i tabeles ne DB
public class Student {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity auto increment
    @Column(name = "id") // emri i kolones ne DB
    private Integer id;

    @Column(name = "first_name")
    private String firstName; // firstName != first_name qe ekziston ne DB prandaj na duhet me perdor @Column

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Student() {}

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
