package com.account.jaxrs.configs;

import com.account.jaxrs.controller.AccountController;
import com.account.jaxrs.patch.JsonPatchReader;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/v1")
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(AccountController.class);
        register(JacksonFeature.class);
        register(JsonPatchReader.class);
    }
}
