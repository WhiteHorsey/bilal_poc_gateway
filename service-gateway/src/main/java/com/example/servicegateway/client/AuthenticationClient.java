package com.example.servicegateway.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AuthenticationClient<S, D> {
    private final Class<D> type;

    @Value("${keycloak-lan.issuer-uri}")
    private String issuerURI;

    @Value("${keycloak-lan.clientId}")
    private String clientId;

    @Value("${keycloak-lan.clientSecret}")
    private String clientSecret;

    public AuthenticationClient(Class<D> type) {
        this.type = type;
    }

    @Autowired
    private RestTemplate restTemplate;

    public D authenticate() {

        // HEADERS => SET x-www-form ...
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // DATA
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "client_credentials");

        // URL
        String url = issuerURI + "/protocol/openid-connect/token";

        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(map, headers), type).getBody();
    }


}