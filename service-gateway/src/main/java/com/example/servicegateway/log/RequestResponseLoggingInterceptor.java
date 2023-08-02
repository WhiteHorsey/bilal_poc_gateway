package com.example.servicegateway.log;

import com.example.servicegateway.utils.BufferingClientHttpResponseWrapper;
import com.example.servicegateway.utils.TimeWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request);
        TimeWatch watch = TimeWatch.start();
        ClientHttpResponse response = execution.execute(request, body);
        String elapsedTime = watch.toMS();
        return logResponse(response, elapsedTime);
    }

    public static void logRequest(HttpRequest request) {
        log.info("REQ : {} URI : {}", request.getMethod(), request.getURI());
    }

    private static ClientHttpResponse logResponse(ClientHttpResponse response, String elapsedTime) throws IOException {
        ClientHttpResponse responseWrapper = new BufferingClientHttpResponseWrapper(response);
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("RES : Status code  : {}, Elapsed time : {}", response.getStatusCode(), elapsedTime);
        } else {
            log.info("RES : Status code  : {}, Elapsed time : {}", response.getStatusCode(), elapsedTime);
        }
        return responseWrapper;
    }

}