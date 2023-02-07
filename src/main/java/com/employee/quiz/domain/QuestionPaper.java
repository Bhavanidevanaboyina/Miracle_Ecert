package com.employee.quiz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "questionpaper")
public class QuestionPaper extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "questionId")
	private Long quizId;
	@Column(name = "question")
	private String question;
	@Column(name = "choice1")
	private String choice1;
	@Column(name = "choice2")
	private String choice2;
	@Column(name = "choice3")
	private String choice3;
	@Column(name = "choice4")
	private String choice4;
	@Column(name = "choice5")
	private String choice5;
	@Column(name = "markedOption")
	private String markedOption;
	@Column(name = "correct_answer")
	private String correct_answer;

	@ManyToOne
	@JoinColumn(name = "quiz_Id")
	private Quiz quiz;

	public QuestionPaper() {

	}

	public Long getQuizId() {
		return quizId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public String getChoice5() {
		return choice5;
	}

	public void setChoice5(String choice5) {
		this.choice5 = choice5;
	}

	public String getMarkedOption() {
		return markedOption;
	}

	public void setMarkedOption(String markedOption) {
		this.markedOption = markedOption;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

}
