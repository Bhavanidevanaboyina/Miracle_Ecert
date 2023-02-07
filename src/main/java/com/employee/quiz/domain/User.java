package com.employee.quiz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

	@Id
	private String userId;
	private String userName;
	private int hubbleId;
	private String gender;
	private String fullName;
}
