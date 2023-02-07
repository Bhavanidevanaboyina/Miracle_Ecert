package com.employee.quiz.util;

import java.util.Collections;



import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
@Service
public class HubbleToken {
	@Value("${loginUrl}")
	public String loginUrl;
//	public ResponseEntity<Map> authenticateHubbleLogin(String loginId, String password, String url) {
//		ResponseEntity<Map> response = null;
//		try {
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//			Map<String, Object> map = new HashMap<>();
//			map.put("loginId", loginId);
//			map.put("password", password);
//			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
//			response = restTemplate.postForEntity(url, entity, Map.class);
//
//		} catch (Exception e) {
//
//		}
//		return response;
//https://uat-hubble-api.miraclesoft.com/v2/mcafe/employee/login
//	}
	
	@Autowired(required = true)
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	public ResponseEntity<Map> hubbleToken(String loginId, String password){
		ResponseEntity<Map> response = null;
		try {
			
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
						Map<String, Object> map = new HashMap<>();
						map.put("loginId", loginId);
						map.put("password", password);
						HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
						response = restTemplate.postForEntity(loginUrl, entity, Map.class);
						System.out.println(response.getBody().get("token"));
						
			
					} catch (Exception e) {
						return null;
			
					}
		return response;
	}
	public JSONObject decodeToken(Map<String, Object> result) {
		JSONObject jsonObject = null;
		try {
			String[] splitString = ((String) result.get("token")).split("\\.");
			String base64EncodedBody = splitString[1];
			Base64 base64Url = new Base64(true);
			String body = new String(base64Url.decode(base64EncodedBody));
			jsonObject = new JSONObject(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}

}
