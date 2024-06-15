package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.example.webservices, com.example.security");
        register(RolesAllowedDynamicFeature.class);
    }
}