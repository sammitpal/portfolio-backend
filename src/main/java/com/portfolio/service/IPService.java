package com.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IPService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> fetchIP() {
        String url = "https://checkip.amazonaws.com";
        return restTemplate.getForEntity(url, String.class);
    }
}
