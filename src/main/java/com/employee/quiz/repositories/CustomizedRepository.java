package com.employee.quiz.repositories;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.employee.quiz.domain.QuestionPaper;
import com.employee.quiz.domain.Quiz;
import com.employee.quiz.domain.Techstack;
import com.employee.quiz.dto.QuestionPaperDto;

public interface CustomizedRepository {

	List<Quiz> getQuizByTechId(String techStackId);

	List<QuestionPaperDto> getQuestionPaperByQuizid(String quizId);

	List<Quiz> getAllMandatoryQuiz();
	
	//List<Techstack> geTechstacksByName(String techStackName);

}