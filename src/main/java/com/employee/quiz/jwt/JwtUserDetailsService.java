package com.employee.quiz.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.password}")
	private String jwtPassword;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (jwtSecret.equals(username)) {
			return new User(jwtSecret, jwtPassword,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Employe not found with username: " + username);
		}
	}
}
