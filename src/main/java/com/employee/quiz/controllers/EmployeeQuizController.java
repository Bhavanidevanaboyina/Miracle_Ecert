package com.employee.quiz.controllers;

import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.BitField;

import javax.websocket.server.PathParam;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.quiz.domain.QuestionPaper;
import com.employee.quiz.domain.Quiz;
import com.employee.quiz.domain.Techstack;
import com.employee.quiz.dto.QuestionPaperDto;
import com.employee.quiz.services.QuestiosPaperService;
import com.employee.quiz.services.QuizStackService;
import com.employee.quiz.services.TechStackService;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;/**
 * Created by Abhinav on 19/1/2023.
 */
@RestController
//@RequestMapping(value = "/employee")
@CrossOrigin(origins = "*")
public class EmployeeQuizController {
	private TechStackService techStackService;

	@Autowired
	public void setTechStackService(TechStackService techStackService) {
		this.techStackService = techStackService;
	}

	private QuizStackService quizStackService;

	@Autowired
	public void setQuizStackService(QuizStackService quizStackService) {
		this.quizStackService = quizStackService;
	}

	private QuestiosPaperService questiosPaperService;

	@Autowired
	public void setQuestiosPaperService(QuestiosPaperService questiosPaperService) {
		this.questiosPaperService = questiosPaperService;
	}

	@GetMapping("/getAllTechStack")
	public List<Techstack> getAllTechStack() {
		return techStackService.getAllTechStack();
	}

	@GetMapping({ "/getQuizByTechStack"})
	public List<Quiz> getAllQuizByTechStack(@PathParam(value = "techStackId") String techStackId) {
		System.out.println("Inside getAllQuestionsByQuizId " + techStackId);
		return quizStackService.getAllQuizByTechStack(techStackId);
	}

	@GetMapping({ "/getQuestionsByQuizId"})
	public List<QuestionPaperDto> getAllQuestionsByQuizId(@PathParam(value = "quizId") String quizId) {
		return questiosPaperService.getAllQuestionsByQuizId(quizId);
	}

	@GetMapping({ "/getMandatoryQuiz" })
	public List<Quiz> getAllMandatoryQuiz() {
		return quizStackService.getAllMandatoryQuiz();
	}
	
	
}
