package com.example.kantinkampus;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role; // "customer" or "admin"
    private String createdAt;

    public static final String ROLE_CUSTOMER = "customer";
    public static final String ROLE_ADMIN = "admin";

    public User() {}

    public User(int id, String username, String password, String name, String email, String phone, String role, String createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Helper methods
    public boolean isAdmin() {
        return ROLE_ADMIN.equals(role);
    }

    public boolean isCustomer() {
        return ROLE_CUSTOMER.equals(role);
    }
}