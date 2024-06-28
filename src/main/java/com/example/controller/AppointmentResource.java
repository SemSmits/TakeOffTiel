package com.example.controller;

import com.example.webservices.Appointment;
import com.example.webservices.Customer;
import com.example.webservices.TakeOffTiel;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;



@Path("/appointment")
public class AppointmentResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAppointment(Appointment appointment, @Context SecurityContext securityContext) {
        try {
            String username = securityContext.getUserPrincipal().getName();
            Customer customer = findCustomer(username);
            appointment.setCustomer(customer);

            appointment.setStatus("pending");

            TakeOffTiel.getTakeOffTiel().addAppointment(appointment);
            System.out.println("Appointment created");
            return Response.ok("Appointment successfully created").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input data").build();
        }
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


