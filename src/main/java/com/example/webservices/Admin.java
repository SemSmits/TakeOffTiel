package com.example.webservices;

import java.io.Serializable;
import java.util.List;

public class Admin extends User implements Serializable {
    private List<String> appointments;

    public Admin(String username, String role, String password, String email, List<String> appointments) {
        super(username, role, password, email);
        this.appointments = appointments;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }
}