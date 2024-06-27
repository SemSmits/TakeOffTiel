package com.example.security;

import com.example.DataUtils;
import com.example.webservices.Customer;
import com.example.webservices.TakeOffTiel;
import com.example.webservices.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.swing.text.TabableView;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Path("/account")
public class AuthenticationResource {

    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) {

        for (User existingUser : TakeOffTiel.getTakeOffTiel().getUsers()) {
            if (existingUser.getUsername().equals(user.getUsername()) && existingUser.getPassword().equals(user.getPassword())) {
                System.out.println("User validated");

                Calendar expiration = Calendar.getInstance();
                expiration.add(Calendar.MINUTE, 30);

                String token = Jwts.builder()
                        .setSubject(existingUser.getUsername())
                        .setExpiration(expiration.getTime())
                        .claim("role", existingUser.getRole())
                        .signWith(SignatureAlgorithm.HS512, key)
                        .compact();
                System.out.println("Token created");
                return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new AbstractMap.SimpleEntry<>("error", "Inloggegevens onjuist"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Customer customer) {
        List<Customer> customers = TakeOffTiel.getTakeOffTiel().getCustomers();

        for (Customer existingCustomer : customers) {
            if (existingCustomer.getUsername().equals(customer.getUsername())) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"error\": \"Username already exists\"}")
                        .build();
            }
        }

        TakeOffTiel.getTakeOffTiel().addCustomer(customer);
        System.out.println("Customer created");
        return Response.ok("{\"message\": \"Registration successful\"}").build();
    }


    public boolean validateUser(User userV) {
        for (User user : TakeOffTiel.getTakeOffTiel().getUsers()) {
            if (user.getUsername().equals(userV.getUsername()) && user.getPassword().equals(userV.getPassword())) {
                System.out.println("User validated");
                return true;
            }
        }
        return false;
    }

}


