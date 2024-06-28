package com.example.webservices;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private Date date;
    private Date time;
    private Customer customer;
    private String status;
    private String receiverName;
    private String receiverEmail;
    private String information;

    public Appointment() {
    }

    public Appointment(String receiverName, String receiverEmail, Date date, Date time, Customer customer, String information, String status) {
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.information = information;
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm - HH:mm")
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
