package com.employee.quiz.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumber {

	public long generateRandomNumber() {
		return Math.round(10000 + Math.random() * 90000);
	}
}
