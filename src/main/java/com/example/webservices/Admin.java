package com.example.webservices;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements Serializable, Principal {
    private static final long serialVersionUID = 1L;
    private ArrayList<Appointment> appointments;

    public Admin() {
        super("", "", "", "", "admin");
    }

    public Admin(String realName, String username, String email, String password, ArrayList<Appointment> appointments) {
        super(realName, username, email, password, "admin");
        this.appointments = appointments;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

}