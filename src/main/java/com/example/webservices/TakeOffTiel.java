package com.example.webservices;

import com.example.util.DataUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class TakeOffTiel implements Serializable {
    private static final long serialVersionUID = 1L;
    private static TakeOffTiel takeOffTiel;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

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

    public static Customer getCustomerByName(String customer) {
        for (Customer customer1 : getTakeOffTiel().customers) {
            if (customer.equals(customer1.getUsername())) {
                return customer1;
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
    public ArrayList<Appointment> getAppointments() {
        return appointments;
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
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addCustomer(Customer customer) {
        this.users.add(customer);
    }

    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }
}