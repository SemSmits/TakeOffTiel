package com.example.webservices;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements Serializable, Principal {
    private static final long serialVersionUID = 1L;
    private ArrayList<Appointment> appointments;

    public Admin(){}

    public Admin(String username, String email, String password, String role, ArrayList<Appointment> appointments) {
        super(username, role, password, email);
        this.appointments = appointments;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String getName() {
        return getUsername();
    }
}