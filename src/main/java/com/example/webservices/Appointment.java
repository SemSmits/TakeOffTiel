package com.example.webservices;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private int id;
    private Date date;
    private Date startTime;
    private Date endTime;
    private String username;
    private String status;
    private String receiverName;
    private String receiverEmail;
    private String dateString;
    private String timeString;
    private String information;

    public Appointment() {
    }

    public Appointment(int id, String receiverName, String receiverEmail, Date date, Date startTime, Date endTime, String username, String information, String status) {
        this.id = id;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.username = username;
        this.information = information;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
