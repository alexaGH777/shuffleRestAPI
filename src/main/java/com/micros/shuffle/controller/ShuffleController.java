package com.micros.shuffle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.micros.shuffle.service.ShuffleService;

@RestController
public class ShuffleController {
	
	@Autowired
	private ShuffleService service;
	
	@Value( "${log.url}" )
	private String logUrl;
	
	@Autowired
    private RestTemplate restTemplate;
 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@PostMapping("/shuffle")
	public String getShuffledArray(@RequestParam String input) {
		
		String arr = service.getShuffledArray(input);
	    // send POST request
	    this.restTemplate.postForObject(logUrl, arr, String.class);

        return arr;
	}
}
