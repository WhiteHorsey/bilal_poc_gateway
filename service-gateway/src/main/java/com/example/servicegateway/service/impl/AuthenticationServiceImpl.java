package com.example.servicegateway.service.impl;

import com.example.servicegateway.client.AuthenticationClient;
import com.example.servicegateway.dto.BaseEntityDTO;
import com.example.servicegateway.dto.KeycloakAuthenticationResponse;
import com.example.servicegateway.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationClient<BaseEntityDTO, KeycloakAuthenticationResponse> authenticationKeycloakClient;

    @Override
    public KeycloakAuthenticationResponse authenticate() {
        return authenticationKeycloakClient.authenticate();
    }
}