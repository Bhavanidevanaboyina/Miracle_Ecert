package com.employee.quiz.services;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;

import com.employee.quiz.domain.QuestionPaper;
import com.employee.quiz.dto.QuestionPaperDto;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Service
public interface QuestiosPaperService {

	List<QuestionPaperDto> getAllQuestionsByQuizId(String quizId);

}
