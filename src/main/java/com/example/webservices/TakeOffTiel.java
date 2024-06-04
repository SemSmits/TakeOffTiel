package com.example.webservices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TakeOffTiel implements Serializable {

    private static TakeOffTiel takeofftiel = new TakeOffTiel();
    private ArrayList<User> users = new ArrayList<>();

    public static TakeOffTiel getTakeofftiel() {
        return takeofftiel;
    }

    public TakeOffTiel() {
    }

    public static User getUserByName(String user) {
        for (User user1 : getTakeofftiel().users) {
            if (user.equals(user1.getUsername())) {
                return user1;
            }
        }
        return null;
    }

    public static void setTakeofftiel(TakeOffTiel takeofftiel1) {
        takeofftiel = takeofftiel1;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}