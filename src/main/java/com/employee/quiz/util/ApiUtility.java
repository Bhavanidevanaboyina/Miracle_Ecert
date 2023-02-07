package com.employee.quiz.util;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ApiUtility {

	private static final Logger logger = LoggerFactory.getLogger(ApiUtility.class);

	@Value("${jwt.secret}")
	private String username;

	@Value("${jwt.password.decrypt}")
	private String password;

	@Autowired
	JdbcTemplate jdbcTemplate;

	String localUrl = "http://172.17.12.110:8081";

	public String generateToken(String loginId, JSONObject jsonObject) {
		logger.info("Entered into generateToken method of ApiUtility");

		String token = "";
		String uri = localUrl + "/authenticate";

		RestTemplate restTemplate = new RestTemplate();
		String user = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		try {

			JSONObject jsonCredentials = new JSONObject();

			jsonCredentials.put("username", username);

			String userDataQuery = "SELECT employe_id, employe_name, phoneNumber,email,address FROM Employe  WHERE loginId=?";

			List<Map<String, Object>> userList = jdbcTemplate.queryForList(userDataQuery, loginId);
			for (Map<String, Object> map : userList) {
				jsonCredentials.put("employe_name", "");
				if (map.get("employe_name") != null && !"".equals(map.get("employe_name").toString().trim())) {
					jsonCredentials.put("employe_name", map.get("employe_name"));
				}

				jsonCredentials.put("employe_id", "");
				if (map.get("employe_id") != null && !"".equals(map.get("employe_id").toString().trim())) {
					jsonCredentials.put("employe_id", map.get("employe_id"));
				}

				jsonCredentials.put("phoneNumber", "");
				if (map.get("phoneNumber") != null && !"".equals(map.get("phoneNumber").toString().trim())) {
					jsonCredentials.put("phoneNumber", map.get("phoneNumber"));
				}

				jsonCredentials.put("email", "");
				if (map.get("email") != null && !"".equals(map.get("email").toString().trim())) {
					jsonCredentials.put("email", map.get("email"));
				}
				jsonCredentials.put("address", "");
				if (map.get("address") != null && !"".equals(map.get("address").toString().trim())) {
					jsonCredentials.put("address", map.get("address"));
				}
				
				jsonCredentials.put("hubble_id", "");
				if (jsonObject.get("empId") != null && !"".equals(jsonObject.get("empId").toString().trim())) {
					jsonCredentials.put("hubble_id", jsonObject.get("empId"));
				}
					
				
				jsonCredentials.put("firstName", "");
				if (jsonObject.get("firstName") != null && !"".equals(jsonObject.get("firstName").toString().trim())) {
					jsonCredentials.put("firstName", jsonObject.get("firstName"));
				}
				
				jsonCredentials.put("middleName", "");
				if (jsonObject.get("middleName") != null && !"".equals(jsonObject.get("middleName").toString().trim())) {
					jsonCredentials.put("middleName", jsonObject.get("middleName"));
				}
				
				jsonCredentials.put("lastName", "");
				if (jsonObject.get("lastName") != null && !"".equals(jsonObject.get("lastName").toString().trim())) {
					jsonCredentials.put("lastName", jsonObject.get("lastName"));
				}
				
				jsonCredentials.put("gender", "");
				if (jsonObject.get("gender") != null && !"".equals(jsonObject.get("gender").toString().trim())) {
					jsonCredentials.put("gender", jsonObject.get("gender"));
				}

			}

			HttpEntity<String> entityCredentials = new HttpEntity<>(jsonCredentials.toString(), httpHeaders);

			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entityCredentials,
					String.class);

			if (responseEntity != null) {
				user = responseEntity.getBody();
			}

			JSONObject json = new JSONObject(user);

			if (json.has("token")) {
				token = (String) json.get("token");
			}

		} catch (Exception e) {
			logger.error("Error has occured in generateToken {}", e.getMessage());
		}
		logger.info("generateToken method ended");
		return token;
	}
}
