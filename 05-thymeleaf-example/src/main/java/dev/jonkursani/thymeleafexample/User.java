package dev.jonkursani.thymeleafexample;

import jakarta.validation.constraints.*;

public class User {
    // per mi perdor annotation per validim duhet me instalu ni dependency Validation
    @NotNull(message = "Name and surname is required")
    @Size(min = 2, max = 30, message = "Name and surname must be between 2 and 30 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must be at most 120")
    private int age;

    @NotNull(message = "Email is required")
    @Email(message = "Email is required")
    // @Pattern for regex expression
//    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
//            message = "Email is required in format example@example.com")
    private String email;

    private String country = "";
    private String favoriteLanguage;
    private String hobbies;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^04\\d(?:-\\d{3}-\\d{3})?$", message = "Phone number must be in format 04X-123-123")
    private String phoneNo;

    public User() {}

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
