package com.example.webservices;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private Date date;
    private Date time;
    private Customer customer;
    private String status;

    public Appointment(Date date, Date time, Customer customer, String status) {
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
