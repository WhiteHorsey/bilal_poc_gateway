package com.example.serviceaccounts.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class TestController {

    @GetMapping("/hello")
    public String greeting(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        System.out.println(auth);
        return "Hello";
    }
}
