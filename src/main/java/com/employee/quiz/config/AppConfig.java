package com.employee.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.quiz.domain.User;

@Configuration
public class AppConfig {
  @Bean
  public User user() {
    return new User();
  }
}