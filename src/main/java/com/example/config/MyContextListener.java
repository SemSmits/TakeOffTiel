package com.example.config;

import com.example.util.DataUtils;
import com.example.webservices.Admin;
import com.example.webservices.TakeOffTiel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing application");

        TakeOffTiel takeOffTiel = DataUtils.getUserData();
        TakeOffTiel.setTakeOffTiel(takeOffTiel);

        boolean adminExists = false;
        for (Admin admin : TakeOffTiel.getTakeOffTiel().getAdmins()) {
            if (admin.getUsername().equals("admin")) {
                adminExists = true;
                break;
            }
        }

        if (!adminExists) {
            Admin newAdmin = new Admin("Tom Groenhof", "admin", "takeofftiel@gmail.com", "password", null, null);
            TakeOffTiel.getTakeOffTiel().addAdmin(newAdmin);
            TakeOffTiel.getTakeOffTiel().addUser(newAdmin);
            System.out.println("Admin created");
        } else {

            System.out.println("Admin retrieved");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Terminating application");

        DataUtils.saveUserData(TakeOffTiel.getTakeOffTiel());
    }
}
