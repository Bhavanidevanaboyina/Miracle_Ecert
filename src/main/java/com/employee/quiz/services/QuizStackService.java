package com.employee.quiz.services;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.quiz.domain.Quiz;
import com.employee.quiz.domain.Techstack;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Service
public interface QuizStackService {

	List<Quiz> getAllQuizByTechStack(String techStackId);

	List<Quiz> getAllMandatoryQuiz();

	Quiz createQuiz(Quiz quiz);
	Map<String, Object> uploadExelFileToQuiz(MultipartFile file, int hubbleId);

}
