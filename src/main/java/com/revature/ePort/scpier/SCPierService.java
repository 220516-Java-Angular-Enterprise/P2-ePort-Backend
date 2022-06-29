package com.revature.ePort.scpier;

import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SCPierService {

    @Inject
    private final RestTemplate restTemplate;

    @Inject
    @Autowired
    public SCPierService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPlainJSON(String name){
        String url = "http://scpier-api.com/api/v1/wiki/scp-006";
        return this.restTemplate.getForObject(url, String.class);
    }
}
