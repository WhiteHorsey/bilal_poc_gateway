//package com.example.servicegateway.filters;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class CustomAuthorizationHeaderFilter implements GatewayFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // Access the original request
//        ServerHttpRequest originalRequest = exchange.getRequest();
//
//        // Create a new HttpHeaders object with your custom Authorization header
//        HttpHeaders newHeaders = new HttpHeaders();
//        String headerValue = "Your-Custom-Auth-Value";
//        newHeaders.add(HttpHeaders.AUTHORIZATION, headerValue);
//
//        // Logging to check original headers
//        System.out.println("Original Headers: " + originalRequest.getHeaders());
//
//        // Create a new ServerHttpRequest with the modified headers
//        ServerHttpRequest modifiedRequest = originalRequest.mutate().headers(httpHeaders -> httpHeaders.addAll(newHeaders)).build();
//
//        // Logging to check modified headers
//        System.out.println("Modified Headers: " + modifiedRequest.getHeaders());
//
//        // Create a new ServerWebExchange with the modified request
//        ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();
//
//        // Continue the filter chain with the modified exchange
//        return chain.filter(modifiedExchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 1;
//    }
//}
