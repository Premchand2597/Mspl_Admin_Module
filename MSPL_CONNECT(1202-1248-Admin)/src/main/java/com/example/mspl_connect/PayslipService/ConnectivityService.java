package com.example.mspl_connect.PayslipService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ConnectivityService {

    private boolean isConnected;

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    @Scheduled(fixedRate = 1000) // Check every 1 seconds
    public void checkConnectivity() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://www.google.com"))
                    .timeout(java.time.Duration.ofSeconds(2))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            isConnected = response.statusCode() == 200;
        } catch (Exception e) {
            isConnected = false;
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}