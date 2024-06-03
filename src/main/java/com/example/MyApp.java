package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class MyApp extends ResourceConfig {
    public MyApp() {
        packages("com.example");
        register(JwtFilter.class);
    }
}