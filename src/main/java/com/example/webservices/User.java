package com.example.webservices;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable, Principal{
    private static final long serialVersionUID = 1L;

    private String realName;
    private String username;
    private String email;
    private String role;
    private String password;

    public User() {
    }

    public User(String realName, String username, String email, String password, String role) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public void setName(String name) {
        this.realName = name;
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


}