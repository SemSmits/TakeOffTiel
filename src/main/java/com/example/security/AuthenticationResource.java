package com.example.security;

import com.example.DataUtils;
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
        DataUtils.getUserData();
        if (validateUser(user)) {
            Calendar expiration = Calendar.getInstance();
            expiration.add(Calendar.MINUTE, 30);

            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(expiration.getTime())
                    .claim("role", user.getRole())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
            System.out.println("token aangemaakt");
            return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Inloggegevens onjuist"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        if (TakeOffTiel.getTakeofftiel().getUsers().contains(user.getUsername())) {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"Username already exists\"}").build();
        } else {
            TakeOffTiel.getTakeofftiel().addUser(user);
            System.out.println("User aangemaakt");
            DataUtils.saveUserData(TakeOffTiel.getTakeofftiel());
            System.out.println("Data opgeslagen");
            return Response.ok("{\"message\": \"Registration successful\"}").build();
        }
    }

    public boolean validateUser(User userV) {
        for (User user : TakeOffTiel.getTakeofftiel().getUsers()) {
            if (user.getUsername().equals(userV.getUsername()) && user.getPassword().equals(userV.getPassword())) {
                System.out.println("user gevalidate");
                return true;
            }
        }
        return false;
    }

}


