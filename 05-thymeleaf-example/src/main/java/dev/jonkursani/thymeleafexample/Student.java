package dev.jonkursani.thymeleafexample;

import dev.jonkursani.thymeleafexample.validations.UniversityCode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Student {
    @NotNull(message = "Ju lutem shkruani emrin dhe mbiemrin.")
    private String name;

    @NotNull(message = "Ju lutem shkruani moshen.")
    @Min(value = 18, message = "Mosha minimale per aplikim eshte 18.")
    @Max(value = 30, message = "Mosha maksimale per aplikim eshte 30.")
    // private int age; // smundet me qene null prandaj na del errori ne konvertim String to int
    private Integer age; // wrapper klasa Integer mundet me qene null edhe e aktivizon @NotNull annotation

    @NotNull(message = "Ju lutem shkruani kodin e univeristetit.")
//    @UniversityCode // CCT, CCT-123 => University code must start with CCT
    @UniversityCode(message = "Kodi i universiteti duhet te filloje me CCT") // CCT, CCT-123 => University code must start with CCT
//    @UniversityCode(value = "UBT", message = "Kodi i universiteti duhet te filloje me UBT") // CCT, CCT-123 => University code must start with CCT
    private String university;

    public Student() {}

    public Student(String name, Integer age, String university) {
        this.name = name;
        this.age = age;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getUniversity() {
        return university;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
