package dev.jonkursani.lombokjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA - i tregon qe kjo klase eshte entitet
@Table(name = "students") // JPA - i tregon emrin e tabeles qe ka mu kriju
@Data // lombok - i tregon qe duhet mi gjeneru geterat edhe seterat per cdo property
@NoArgsConstructor // lombok - i tregon qe duhet me gjeneru konstruktorin pa parametra (default)
@AllArgsConstructor // lombok - i tregon qe duhet me gjeneru konstruktorin me parametra
public class Student {
    @Id // JPA - i tregon qe kjo property eshte id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // nullable = false => name not null
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "university", nullable = false, length = 80)
    private String university;

    @Column(name = "license_no", nullable = false, length = 20, unique = true)
    private String licenseNo;

    // public Student() {}

//    public Student(String name, int age, String university) {
//        this.name = name;
//        this.age = age;
//        this.university = university;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public String getUniversity() {
//        return university;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setUniversity(String university) {
//        this.university = university;
//    }
}