package com.Tubes.VapeConnects.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;      

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    private String age; 

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
    public String getAge() {
    return age;
    }

    public void setAge(String age) {
    this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email; 
    }


    public void login(String username, String password) {
        // user ogin
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful for user: " + username);
        } else {
            System.out.println("Login failed for user: " + username);
        }
    }
    public void register(String username, String email, String password) {
        // user regis
        this.username = username;
        this.email = email;
        this.password = password;
        System.out.println("User registered: " + username);
    }
}