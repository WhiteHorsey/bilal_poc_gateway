package com.example.servicegateway.service;

import com.example.servicegateway.dto.KeycloakAuthenticationResponse;

public interface AuthenticationService {
    KeycloakAuthenticationResponse authenticate();
}
