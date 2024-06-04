package com.example.security;

import com.example.webservices.TakeOffTiel;
import com.example.webservices.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("initializing application");
        TakeOffTiel.getTakeofftiel().addUser(new User("user", "user@mail.com", "password", "user"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");
    }
}
