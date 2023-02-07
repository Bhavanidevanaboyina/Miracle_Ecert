package com.employee.quiz.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;

import com.employee.quiz.domain.QuestionPaper;
import com.employee.quiz.domain.Quiz;
import com.employee.quiz.domain.Techstack;
import com.employee.quiz.dto.QuestionPaperDto;

@Service
public class CustomizedRepositoryImpl implements CustomizedRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Quiz> getQuizByTechId(String techStackId) {
		System.out.println("Inside GetQuizByTechId --> implement" + techStackId);
		List<Quiz> quizList = entityManager
				.createNativeQuery("SELECT * " + "FROM quiz " + "WHERE tech_stack_id = :techId", Quiz.class)
				.setParameter("techId", techStackId).getResultList();
		return quizList;
	}

	@Override
	public List<QuestionPaperDto> getQuestionPaperByQuizid(String quizId) {
		System.out.println("Inside GetQuestionPaperByQuizid --> implement" + quizId);
		List<QuestionPaperDto> questionPaperDto = new ArrayList<>();
		List<QuestionPaper> questionList = entityManager
				.createNativeQuery("select * from questionpaper where quiz_id=:quizId", QuestionPaper.class)
				.setParameter("quizId", quizId).getResultList();
		for(QuestionPaper paper: questionList) {
			QuestionPaperDto dto = new QuestionPaperDto();
			dto.setQuizId(paper.getQuizId());
			dto.setChoice1(paper.getChoice1());
			dto.setChoice2(paper.getChoice2());
			dto.setChoice3(paper.getChoice3());
			dto.setChoice4(paper.getChoice4());
			dto.setQuestion(paper.getQuestion());
			questionPaperDto.add(dto);
				
			
		}
		return questionPaperDto;
	}

	@Override
	public List<Quiz> getAllMandatoryQuiz() {
		System.out.println("Inside getAllMandatoryQuiz --> implement");
		List<Quiz> mandatoryQuizList = entityManager
				.createNativeQuery("select * from quiz where is_mandatory=true", Quiz.class).getResultList();
		return mandatoryQuizList;
	}

//	@Override
//	public List<Techstack> geTechstacksByName(String techStackName) {
//		// TODO Auto-generated method stub
//		List<Techstack> list = entityManager
//				.createNativeQuery("SELECT tech_stack_id FROM testdb.techstack WHERE tech_stack_name =:tech_stack_name", Techstack.class)
//				.setParameter("tech_stack_name", techStackName).getResultList();
//		return list;
//	}

}