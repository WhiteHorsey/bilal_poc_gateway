package com.example.servicegateway.filters;

import com.example.servicegateway.dto.KeycloakAuthenticationResponse;
import com.example.servicegateway.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomPostTokenValidationFilter implements GlobalFilter, Ordered {

    private final AuthenticationService authenticationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // Access the original request
        ServerHttpRequest originalRequest = exchange.getRequest();

        log.info("First Pre Global Filter with Token : {}", originalRequest.getHeaders().getFirst(AUTHORIZATION));


        KeycloakAuthenticationResponse response = authenticationService.authenticate();
        log.info("KEYCLOAK LAN TOKEN {}", response.getAccessToken());


        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.add(AUTHORIZATION, "Bearer " + response.getAccessToken());


        // Create a new ServerHttpRequest with the modified headers
        ServerHttpRequest modifiedRequest = new ServerHttpRequestDecorator(originalRequest) {
            @Override
            public HttpHeaders getHeaders() {
                return newHeaders;
            }
        };

        ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();


        // Logging to check modified headers
        log.info("Modified Headers: " + modifiedExchange.getRequest().getHeaders().getFirst(AUTHORIZATION));


        return chain.filter(modifiedExchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("Last Post Global Filter with Token : {}", modifiedExchange.getRequest().getHeaders().getFirst(AUTHORIZATION));
                }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}