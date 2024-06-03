package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/auth")
public class AuthResource {

    private static final Map<String, User> dummyUsers = new HashMap<>();

    static {
        // Voeg dummy gebruikers toe in het User format
        dummyUsers.put("user", new User("user", "password", "user@example.com", "user"));
        dummyUsers.put("admin", new User("admin", "adminpassword", "admin@example.com", "admin"));
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials user) {
        if ("user".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            String token = JwtUtil.generateToken(user.getUsername());
            return Response.ok(new AuthResponse(token)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public class UserCredentials {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
