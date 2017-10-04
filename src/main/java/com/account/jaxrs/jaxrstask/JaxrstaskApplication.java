package com.account.jaxrs.jaxrstask;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({"com.account.jaxrs"})
public class JaxrstaskApplication extends SpringBootServletInitializer {

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter protoMsgConv) {
        return new RestTemplate(Arrays.asList(protoMsgConv));
    }

    public static void main(String[] args) {
        new JaxrstaskApplication().configure(new SpringApplicationBuilder(JaxrstaskApplication.class)).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JaxrstaskApplication.class);
    }
}
