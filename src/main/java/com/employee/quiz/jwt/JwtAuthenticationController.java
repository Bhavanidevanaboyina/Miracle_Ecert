package com.employee.quiz.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Value("${jwt.password.decrypt}")
	private String password;

	@PostMapping(value = "/authenticate")

	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), password);

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		Map<String, Object> claims = new HashMap<>();
		claims.put("employe_id", authenticationRequest.getEmploye_id());
		claims.put("employe_name", authenticationRequest.getEmploye_name());
		claims.put("phoneNumber", authenticationRequest.getPhoneNumber());
		claims.put("email", authenticationRequest.getEmail());
		claims.put("address", authenticationRequest.getAddress());
		claims.put("hubble_id", authenticationRequest.getHubble_id());
		claims.put("firstName", authenticationRequest.getFirstName());
		claims.put("middleName", authenticationRequest.getMiddleName());
		claims.put("lastName", authenticationRequest.getLastName());
		claims.put("gender", authenticationRequest.getGender());
		
		
		final String token = jwtTokenUtil.generateToken(userDetails, claims);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("EMPLOYE_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
