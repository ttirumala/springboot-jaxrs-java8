package com.account.jaxrs.jaxrstask;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.account.jaxrs"})
public class JaxrstaskApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new JaxrstaskApplication().configure(new SpringApplicationBuilder(JaxrstaskApplication.class)).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JaxrstaskApplication.class);
    }
}
