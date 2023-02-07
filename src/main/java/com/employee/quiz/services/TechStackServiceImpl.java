package com.employee.quiz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.quiz.domain.Techstack;
import com.employee.quiz.repositories.TechStackRepository;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Service
public class TechStackServiceImpl implements TechStackService {

	private TechStackRepository techStackRepository;

	@Autowired
	public TechStackServiceImpl(TechStackRepository techStackRepository) {
		this.techStackRepository = techStackRepository;
	}

	@Override
	public List<Techstack> getAllTechStack() {
		return (List<Techstack>) techStackRepository.findAll();
	}

	@Override
	public Techstack createTechStack(Techstack techStack) {
		return techStackRepository.save(techStack);
	}

	@Override
	public Optional<Techstack> findById(String techStackId) {
		return techStackRepository.findById(techStackId);
	}
}
