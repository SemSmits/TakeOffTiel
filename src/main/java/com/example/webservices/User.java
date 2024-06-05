package com.example.webservices;
import javax.security.auth.Subject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String username;
    private String email;
    private String role;
    private String password;

    public User(){}
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    public String getUsername() {
        return username;
    }
}