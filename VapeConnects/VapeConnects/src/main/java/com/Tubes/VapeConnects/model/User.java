package com.Tubes.VapeConnects.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    public User() {
    }
    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void login(String username, String password) {
        // Logic for user login
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful for user: " + username);
        } else {
            System.out.println("Login failed for user: " + username);
        }
    }
    public void register(String username, String email, String password) {
        // Logic for user registration
        this.username = username;
        this.email = email;
        this.password = password;
        System.out.println("User registered: " + username);
    }
}
