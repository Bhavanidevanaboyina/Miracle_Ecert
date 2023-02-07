package com.employee.quiz.authentication;

import java.util.HashMap;





import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.employee.quiz.util.ApiUtility;
import com.employee.quiz.util.HubbleToken;
import com.employee.quiz.util.UserProfile;








@Repository
public class AuthenticationDao {

		private static final Logger logger = LoggerFactory.getLogger(AuthenticationDao.class);

		@Autowired
		JdbcTemplate jdbcTemplate;

		@Autowired
		HttpServletRequest request;
		
		@Autowired
		HubbleToken hubbleToken;
		
		@Autowired
		UserProfile userProfile;
		
		@Value("${loginUrl}")
		public String loginUrl;

		@Autowired
		ApiUtility apiUtility;

		private String res = "success";
		private String msg = "message";
		private String defaultMsg = "Invalid inputs!";

		public Map<String, Object> employeLogin(Authentication authentication) throws Exception {
			logger.info("Entered into employeLogin method of AuthenticationDao");
			Map<String, Object> response = new HashMap<>();
			response.put(msg, "Oops something wrong");
			response.put(res, false);

			Map<String, Object> data = new HashMap<>();
			String loginId = authentication.getLoginId();
			String hubbletoken = "";
			List<Map<String, Object>> dataList = new ArrayList<>();
			try {
				String query = "SELECT employe_id, employe_name, phoneNumber,email FROM Employe WHERE loginId=?";

				dataList = jdbcTemplate.queryForList(query, loginId);
				System.out.println(dataList);
				if (!dataList.isEmpty()) {
					System.out.println("email--" + loginId);

					if (authentication.getLoginId() != null) {

						ResponseEntity<Map> restResponse = hubbleToken.hubbleToken(authentication.getLoginId(),
								authentication.getPassword());
						Map<String, Object> result = restResponse.getBody();
						JSONObject jsonObject = hubbleToken.decodeToken(result);
						userProfile.storeUserInformation(jsonObject);
						hubbletoken = (String) result.get("token");
					String token = apiUtility.generateToken(loginId,jsonObject);
					data.put("token", token);
					response.put(res, true);
					response.put("hubbleToken", hubbletoken);
					response.put(msg, "Employe logged in succesfully");
					response.put("data", data);
				} else {
					response.put(res, false);
					response.put(msg, "Invalid email Id");
				}
				}
			} catch (Exception e) {
				logger.error("Error has occured in userCheck {}", e.getMessage());
			}
			logger.info("employeLogin method ended");
			return response;
			

	}

}
