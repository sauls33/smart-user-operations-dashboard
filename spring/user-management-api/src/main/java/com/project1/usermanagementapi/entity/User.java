package com.project1.usermanagementapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String department;

    private LocalDate lastLoginDate;

    @Column(nullable = false)
    private String riskLevel;

    public User() {
    }

    public User(Long id, String name, String email, boolean active, String role, String department, LocalDate lastLoginDate, String riskLevel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
        this.role = role;
        this.department = department;
        this.lastLoginDate = lastLoginDate;
        this.riskLevel = riskLevel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}