package com.revature.ePort.scpier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SCPierService {

    @Inject
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Inject
    @Autowired
    public SCPierService(RestTemplate restTemplate,ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public JsonNode getPlainJSON(String name){
        String url = "http://scpier-api.com/api/v1/wiki/" + name + "?language=ENGLISH";
        String jsonStr = restTemplate.getForObject(url, String.class);
        try {
            JsonNode test = objectMapper.readTree(jsonStr);
            JsonNode content = test.at("/content").get(0);
            return content;
        }catch (Exception e){
            throw new InvalidRequestException("Could not find SCP");
        }
    }

}


