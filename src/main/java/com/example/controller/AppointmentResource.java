package com.example.controller;

import com.example.util.AppointmentStatusUpdate;
import com.example.webservices.Admin;
import com.example.webservices.Appointment;
import com.example.webservices.Customer;
import com.example.webservices.TakeOffTiel;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/appointment")
public class AppointmentResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAppointment(Appointment appointment, @Context SecurityContext securityContext) {
        try {
            String username = securityContext.getUserPrincipal().getName();
            Customer customer = findCustomer(username);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(appointment.getDateString());
            appointment.setDate(date);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String[] timeRange = appointment.getTimeString().split(" - ");
            if (timeRange.length == 2) {
                appointment.setStartTime(timeFormat.parse(timeRange[0]));
                appointment.setEndTime(timeFormat.parse(timeRange[1]));
            }

            appointment.setStatus("pending");
            appointment.setId(generateID());

            if(customer != null) {
                appointment.setUsername(customer.getUsername());
                customer.addAppointment(appointment);
            }

            for (Admin admin : TakeOffTiel.getTakeOffTiel().getAdmins()) {
                if (admin != null) {
                    admin.addAppointment(appointment);
                }
            }

            return Response.ok("Appointment successfully created").build();
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date or time format").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam("id") int id, @Context SecurityContext securityContext, AppointmentStatusUpdate statusUpdate) {
        String username = securityContext.getUserPrincipal().getName();

        Admin admin = findAdmin(username);
        if (admin != null) {
            for (Appointment appointment : admin.getAppointments()) {
                if (appointment.getId() == id) {
                    appointment.setStatus(statusUpdate.getStatus());
                    System.out.println("Status updated");
                    TakeOffTiel.saveTakeOffTiel();
                    return Response.ok(appointment).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found").build();
                }
            }
        }
        return null;
    }

    private Customer findCustomer(String username) {
        for (Customer customer : TakeOffTiel.getTakeOffTiel().getCustomers()) {
            if (customer.getUsername().equals(username)) {
                System.out.println("User found");
                return customer;
            }
        }
        return null;
    }

    private Admin findAdmin(String username) {
        for (Admin admin : TakeOffTiel.getTakeOffTiel().getAdmins()) {
            if (admin.getUsername().equals(username)) {
                System.out.println("User found");
                return admin;
            }
        }
        return null;
    }

    public static synchronized int generateID() {
        int idCounter = TakeOffTiel.getTakeOffTiel().getIdCounter().getValue();
        idCounter++;
        TakeOffTiel.getTakeOffTiel().getIdCounter().setValue(idCounter);
        TakeOffTiel.saveTakeOffTiel();
        return idCounter;
    }
}

