package com.employee.quiz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.quiz.domain.QuestionPaper;
import com.employee.quiz.dto.QuestionPaperDto;
import com.employee.quiz.repositories.CustomizedRepository;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Service
public class QuestionPaperServiceImpl implements QuestiosPaperService {

	private CustomizedRepository customizedRepository;

	@Autowired
	public QuestionPaperServiceImpl(CustomizedRepository customizedRepository) {
		this.customizedRepository = customizedRepository;
	}

	@Override
	public List<QuestionPaperDto> getAllQuestionsByQuizId(String quizId) {
		return customizedRepository.getQuestionPaperByQuizid(quizId);
	}
}
