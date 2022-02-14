package com.jvscorp.jobportal.userprofileservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class UserProfileRestService {
	
	@Autowired
	private EurekaClient client;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuiler;
	
	
	
	@GetMapping("/")
	public String profileHeading() {
		
		RestTemplate template = restTemplateBuiler.build();
		InstanceInfo instanceInfo = client.getNextServerFromEureka("user-service", false);
		String baseUrl = instanceInfo.getHomePageUrl();
		return template.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		
		 
	}

}
