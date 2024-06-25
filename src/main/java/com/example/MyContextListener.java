package com.example;

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

        if(!TakeOffTiel.getTakeofftiel().getUsers().contains("user")){
            TakeOffTiel.getTakeofftiel().addUser(new User("user", "user@mail.com", "password", "admin"));
            System.out.println("user aangemaakt");
        }


        TakeOffTiel takeOffTiel = DataUtils.getUserData();
        if (takeOffTiel != null) {
            TakeOffTiel.setTakeofftiel(takeOffTiel);
        } else {
            takeOffTiel = TakeOffTiel.getTakeofftiel();
        }
        DataUtils.saveUserData(takeOffTiel);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");

        DataUtils.saveUserData(TakeOffTiel.getTakeofftiel());
    }
}
