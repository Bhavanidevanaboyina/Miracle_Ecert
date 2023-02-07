package com.employee.quiz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.quiz.domain.Techstack;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Service
public interface TechStackService {

	List<Techstack> getAllTechStack();

	Techstack createTechStack(Techstack techStack);

	Optional<Techstack> findById(String techStackId);

}
