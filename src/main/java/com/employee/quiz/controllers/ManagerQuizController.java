package com.employee.quiz.controllers;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.quiz.domain.Quiz;
import com.employee.quiz.domain.Techstack;
import com.employee.quiz.domain.User;
import com.employee.quiz.repositories.UserRepository;
import com.employee.quiz.services.QuizStackService;
import com.employee.quiz.services.TechStackService;
import com.employee.quiz.util.RandomNumber;

/**
 * Created by Abhinav on 19/1/2023.
 */
@RestController
public class ManagerQuizController {

	@Autowired
	User user;
	@Autowired
	UserRepository userRepository;
	private TechStackService techStackService;

	@Autowired
	private RandomNumber randomNumber;

	@Autowired
	public void setTechStackService(TechStackService techStackService) {
		this.techStackService = techStackService;
	}

	private QuizStackService quizStackService;

	@Autowired
	public void setQuizStackService(QuizStackService quizStackService) {
		this.quizStackService = quizStackService;
	}

	@PostMapping("/manager/createTechStack")
	public Techstack createTechStack(@PathParam(value = "techStackCategory") String techStackCategory,
			@PathParam(value = "createdBy") String createdBy) {
		Techstack techStack = initTechStack(techStackCategory, createdBy);
		return techStackService.createTechStack(techStack);
	}

	@PostMapping("/manager/createQuiz/{hubbleId}")
	public Quiz createQuiz(@PathParam(value = "quizName") String quizName,
			@PathParam(value = "techStackId") String techStackId, @PathParam(value = "createdBy") String createdBy,
			@PathParam(value = "quizExpireDate") String quizExpireDate,@PathVariable int hubbleId,@RequestBody Quiz quizdata) {
		Quiz quiz = initQuiz(quizName, createdBy, techStackId, quizExpireDate,hubbleId,quizdata);
		return quizStackService.createQuiz(quiz);
	}

	@PostMapping("/upload/{hubbleId}")
	  public Map<String,Object> uploadExelFileToQuiz(@RequestParam(value = "file") MultipartFile file,@PathVariable int hubbleId) throws InvalidFormatException {
		System.out.println("hello");
		  return quizStackService.uploadExelFileToQuiz(file,hubbleId);
	    
	  }
	private Quiz initQuiz(String quizName, String createdBy, String techStackId, String quizExpireDate, int hubbleId, Quiz quizdata) {
		Quiz quiz = new Quiz();
		String name;
		name=userRepository.findIdByName(hubbleId);
		quiz.setQuizName(quizName);
		quiz.setQuizId(UUID.randomUUID().toString());
		Optional<Techstack> techStack = techStackService.findById(techStackId);
		quiz.setTechstack(techStack.get());
		quiz.setCreatedBy(name);
		quiz.setLastModifiedBy(name);
		quiz.setCreationDate(java.time.LocalDate.now());
		quiz.setLastModifiedDate(java.time.LocalDate.now());
		quiz.setQuizCreatedDate(java.time.LocalDate.now());
		quiz.setQuizExpiredDate(LocalDate.parse(quizExpireDate));
		quiz.setOpenQuiz(quizdata.getOpenQuiz());
		quiz.setUnAssignedQuiz(quizdata.getUnAssignedQuiz());
		quiz.setQuizDescription(quizdata.getQuizDescription());
		quiz.setQuizNotice(quizdata.getQuizNotice());
		quiz.setQuizTime(quizdata.getQuizTime());
		
		return quiz;
	}

	private Techstack initTechStack(String techStackCategory, String createdBy) {
		Techstack techStack = new Techstack();
		techStack.setTechStackId(UUID.randomUUID().toString());
		techStack.setTechStackName(techStackCategory);
		techStack.setCreatedBy(createdBy);
		techStack.setLastModifiedBy(createdBy);
		techStack.setCreationDate(java.time.LocalDate.now());
		techStack.setLastModifiedDate(java.time.LocalDate.now());
		System.out.println("TechStack ::::::" + techStack);
		return techStack;
	}
}
