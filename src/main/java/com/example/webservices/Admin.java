package com.example.webservices;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements Serializable, Principal {
    private static final long serialVersionUID = 1L;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    private ArrayList<Review> reviews = new ArrayList<>();

    public Admin() {
        super("", "", "", "", "admin");
    }

    public Admin(String realName, String username, String email, String password, ArrayList<Appointment> appointments, ArrayList<Review> reviews) {
        super(realName, username, email, password, "admin");
        if (appointments == null) {
            this.appointments = new ArrayList<>();
        } else {
            this.appointments = new ArrayList<>(appointments);
        }

        if (reviews == null) {
            this.reviews = new ArrayList<>();
        } else {
            this.reviews = new ArrayList<>(reviews);
        }
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}