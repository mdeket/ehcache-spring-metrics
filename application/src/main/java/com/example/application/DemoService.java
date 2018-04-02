package com.example.application;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {

    @Cacheable("fakeSlowData")
    public String getCachedData(){
        RestTemplate requestTemplate = new RestTemplate();
        ResponseEntity response = requestTemplate.getForEntity("http://www.fakeresponse.com/api/?sleep=3", String.class);
        return (String)response.getBody();
    }

    public String getData(){
        RestTemplate requestTemplate = new RestTemplate();
        ResponseEntity response = requestTemplate.getForEntity("http://www.fakeresponse.com/api/?sleep=3", String.class);
        return (String)response.getBody();
    }

}
