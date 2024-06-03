package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/auth")
public class AuthResource {

    public static Map<String, User> users = new HashMap<>();

    static {
        User adminUser = new User("admin", "adminpassword", "admin@example.com", "admin");
        users.put(adminUser.getUsername(), adminUser);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email,
            @FormParam("role") String role) {
        if (users.containsKey(username)) {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"Username already exists\"}").build();

        } else {
            User newUser = new User(username, password, email, role);
            users.put(username, newUser);
            String token = JwtUtil.createToken(username, role);
            return Response.ok("{\"token\": \"" + token + "\"}").build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
            @FormParam("password") String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            String token = JwtUtil.createToken(username, user.getRole());
            return Response.ok("{\"token\": \"" + token + "\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Invalid credentials\"}").build();
        }
    }
}
