package com.employee.quiz.jwt;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class JwtRequest implements Serializable {

	private int employe_id;
	private String employe_name;
	private String email;
	private String phoneNumber;
	private String employename;
	private String username;
	private String address;
	private int hubble_id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String gender;
	

}
