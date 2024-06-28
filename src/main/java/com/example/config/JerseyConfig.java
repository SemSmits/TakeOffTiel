package com.example.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.example");
        register(RolesAllowedDynamicFeature.class);
    }
}