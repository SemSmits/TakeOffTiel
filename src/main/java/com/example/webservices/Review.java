package com.example.webservices;

import java.io.Serializable;

public class Review implements Serializable {
    private String content;
    private String user;
    private int id;

    public Review(){}

    public Review(int id, String user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
