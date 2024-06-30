package com.example.controller;

import com.example.webservices.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;

@Path("/review")
public class ReviewResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointments(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        LocalDate currentDate = LocalDate.now();
        try {
            List<Appointment> appointments = getAcceptedAppointments(username, currentDate);
            return Response.ok(appointments).build();
        } catch (Exception e) {
            JsonObject errorMessage = Json.createObjectBuilder()
                    .add("error", "Failed to retrieve appointments.")
                    .build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorMessage)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitReview(Review review, @Context SecurityContext securityContext) {
        try {
            String username = securityContext.getUserPrincipal().getName();
            review.setUser(username);

            for (Admin admin : TakeOffTiel.getTakeOffTiel().getAdmins()) {
                admin.addReview(review);
                TakeOffTiel.saveTakeOffTiel();
                return Response.ok(admin).build();
            }

            return Response.status(Response.Status.NOT_FOUND).entity("Admin not found").build();
        } catch (Exception e) {
            JsonObject errorMessage = Json.createObjectBuilder()
                    .add("error", "Failed to submit review.")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .build();
        }
    }

    private List<Appointment> getAcceptedAppointments(String username, LocalDate currentDate) {
        List<Appointment> allAppointments = new ArrayList<>();
        for (Customer customer : TakeOffTiel.getTakeOffTiel().getCustomers()) {
            if (customer.getUsername().equals(username)) {
                allAppointments.addAll(customer.getAppointments());
            }
        }

        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if ("accepted".equals(appointment.getStatus()) && LocalDate.parse(appointment.getDateString()).isBefore(currentDate) && !getReviewId(appointment.getId())) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }

    private boolean getReviewId(int appointmentId) {
        List<Review> reviews = new ArrayList<>();
        for (Admin admin : TakeOffTiel.getTakeOffTiel().getAdmins()) {
            reviews.addAll(admin.getReviews());
        }

        for (Review review : reviews) {
            if (review.getId() == appointmentId) {
                return true;
            }
        }
        return false;
    }
}
