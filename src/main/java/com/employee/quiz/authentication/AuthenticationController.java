package com.employee.quiz.authentication;

import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	AuthenticationDao authenticationDao;


	@PostMapping("/login")
	public Map<String, Object> employeLogin(@RequestBody Authentication authentication ) throws Exception {
		return authenticationDao.employeLogin(authentication);
	}
}
