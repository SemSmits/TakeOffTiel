package com.example.webservices;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable, Principal{
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String role;
    private String password;

    public User() {
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String getName() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "role='" + role + '\'' +
                '}';
    }
}