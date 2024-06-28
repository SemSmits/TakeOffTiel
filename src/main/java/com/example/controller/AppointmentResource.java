package com.example.controller;

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
import java.util.List;

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

            appointment.setCustomer(customer);
            appointment.setStatus("pending");

            for (Admin admin : TakeOffTiel.getTakeOffTiel().getAdmins()) {
                if (admin != null) {
                    admin.addAppointment(appointment);
                }
            }

            if (customer != null) {
                customer.addAppointment(appointment);
            }


            return Response.ok("Appointment successfully created").build();
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date or time format").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        List<Appointment> appointments = TakeOffTiel.getTakeOffTiel().getAppointments();
        return Response.ok(appointments).build();
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
}


