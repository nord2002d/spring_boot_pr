package com.example.spring_boot_project.web.model;


import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Validated
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank( message = "не может быть пустым, заполните поле")
    @Column(name = "name")
    private String name;
    @NotBlank( message = "не может быть пустым, заполните поле")
    @Column(name = "sur_name")
    private String surName;
    @Min(value = 1, message = "Минимальное значение 1, укажите корректное значение")
    @Max(value = 130, message = "Максимальное значение 130, укажите корректное значение")
    @Positive( message = "не может быть пустым, заполните поле")
    @Column(name = "age")
    private int age;

    public User() {
    }

    public User(String name, String surName, int age) {
        this.name = name;
        this.surName = surName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surName, user.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, age);
    }
}
