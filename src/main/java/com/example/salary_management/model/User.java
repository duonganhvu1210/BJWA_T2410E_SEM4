package com.example.salary_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique = true)   // ← đảm bảo không trùng tên
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;

    @Min(value = 0, message = "Salary must be positive")
    private double salary;

    // Constructors
    public User() {}
    public User(String name, int age, double salary) {
        this.name = name; this.age = age; this.salary = salary;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}