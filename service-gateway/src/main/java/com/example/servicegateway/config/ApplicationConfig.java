package com.example.servicegateway.config;

import com.example.servicegateway.client.AuthenticationClient;
import com.example.servicegateway.dto.BaseEntityDTO;
import com.example.servicegateway.dto.KeycloakAuthenticationResponse;
import com.example.servicegateway.log.RequestResponseLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class ApplicationConfig {

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        // INIT
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        // ERROR HANDLER
//        restTemplate.setErrorHandler(new MyResponseErrorHandler());
        // LOG INTERCEPTOR
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        // REST TEMPLATE
        return restTemplate;
    }

    // AUTHENTICATION CLIENT
    @Bean
    public AuthenticationClient<BaseEntityDTO, KeycloakAuthenticationResponse> authenticationKeycloakClient() {
        return new AuthenticationClient<>(KeycloakAuthenticationResponse.class);
    }

}