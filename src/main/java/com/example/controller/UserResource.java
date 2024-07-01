package com.example.controller;

import com.example.util.PasswordChanger;
import com.example.webservices.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;

@Path("/user")
public class UserResource {

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails() {
        try {
            User user = (User) securityContext.getUserPrincipal();
            if (user == null) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            return Response.ok(user).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/password")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserPassword(PasswordChanger request) {
        try {
            User user = (User) securityContext.getUserPrincipal();
            if (user == null) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            user.setPassword(request.getPassword());
            return Response.ok(user).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
