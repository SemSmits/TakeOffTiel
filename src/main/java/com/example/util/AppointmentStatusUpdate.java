package com.example.util;

public class AppointmentStatusUpdate {
    private String status;

    public AppointmentStatusUpdate() {
    }

    public AppointmentStatusUpdate(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}