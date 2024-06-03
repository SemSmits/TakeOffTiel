package com.example;

import io.jsonwebtoken.Claims;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/user")
public class UserResource {

    @GET
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public User getProfile(@Context SecurityContext securityContext) {
        Claims claims = (Claims) securityContext.getUserPrincipal();
        String username = claims.getSubject();
        User user = AuthResource.users.get(username);
        return user;
    }
}
