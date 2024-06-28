package com.example.webservices;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable, Principal {
    private static final long serialVersionUID = 1L;
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Review> reviews;

    public Customer(){
        super("", "", "", "", "customer");
    }

    public Customer(String realName, String username, String email, String password, ArrayList<Appointment> appointments, ArrayList<Review> reviews) {
        super(realName, username, email, password, "customer");
        this.appointments = appointments;
        this.reviews = reviews;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

}
