package com.example.security;

import com.example.webservices.TakeOffTiel;
import com.example.webservices.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.swing.text.TabableView;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Path("/auth")
public class AuthenticationResource {

    public static final Key key = MacProvider.generateKey();


    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        if (validateUser(user)) {
            Calendar expiration = Calendar.getInstance();
            expiration.add(Calendar.MINUTE, 30);

            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(expiration.getTime())
                    .claim("role", user.getRole())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
            return Response.ok(new AbstractMap.SimpleEntry<>("Jwt", token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Inloggegevens onjuist"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }


    private boolean validateUser(User userV) {
        for (User user : TakeOffTiel.getTakeofftiel().getUsers()) {
            if (user.getUsername().equals(userV.getUsername()) && user.getPassword().equals(userV.getPassword())) {
                return true;
            }
        }
        return false;
    }
}


