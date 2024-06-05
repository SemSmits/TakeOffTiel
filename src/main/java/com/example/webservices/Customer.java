package com.example.webservices;

import java.util.List;

public class Customer extends User{
    private List<String> appointments;
    private List<String> reviews;

    public Customer(String username, String role, String password, String email, List<String> appointments, List<String> reviews) {
        super(username, role, password, email);
        this.appointments = appointments;
        this.reviews = reviews;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
