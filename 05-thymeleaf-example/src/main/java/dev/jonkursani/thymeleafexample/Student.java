package dev.jonkursani.thymeleafexample;

public class Student {
    private String name;
    private int age;
    private String university;

    public Student() {}

    public Student(String name, int age, String university) {
        this.name = name;
        this.age = age;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getUniversity() {
        return university;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
