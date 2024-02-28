package com.georg.boredapi.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ActivityService {

    private static String urlApi = "https://www.boredapi.com/api/{action}";
    private final RestTemplate restTemplate;

    public String getInf(String action) {
        String url = urlApi.replace("{action}", action);
        return restTemplate.getForObject(url, String.class);
    }

}
