package com.example.webservices;

import com.example.util.DataUtils;
import com.example.util.IdCounter;

import java.io.Serializable;
import java.util.ArrayList;

public class TakeOffTiel implements Serializable {
    private static final long serialVersionUID = 1L;
    private static TakeOffTiel takeOffTiel;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();
    private IdCounter idCounter;


    public TakeOffTiel() {
    }

    public static User getUserByName(String user) {
        for (User user1 : getTakeOffTiel().users) {
            if (user.equals(user1.getUsername())) {
                return user1;
            }
        }
        return null;
    }

    public static TakeOffTiel getTakeOffTiel() {
        if (takeOffTiel == null) {
            takeOffTiel = new TakeOffTiel();
        }
        return takeOffTiel;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> allAppointments = new ArrayList<>();
        for (Admin admin : admins) {
            allAppointments.addAll(admin.getAppointments());
        }
        return allAppointments;
    }

    public void setIdCounter(IdCounter idCounter) {
        this.idCounter = idCounter;
    }

    public IdCounter getIdCounter() {
        if (idCounter == null) {
            return new IdCounter(0);
        }
        return idCounter;
    }

    public static void setTakeOffTiel(TakeOffTiel takeofftiel1) {
        takeOffTiel = takeofftiel1;
    }

    public static void saveTakeOffTiel(){
        DataUtils.saveUserData(TakeOffTiel.getTakeOffTiel());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }
}