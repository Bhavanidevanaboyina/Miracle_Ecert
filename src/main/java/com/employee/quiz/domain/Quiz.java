package com.employee.quiz.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "quiz")
public class Quiz extends Auditable<String> {

	@Id
	@Column(name = "quizId")
	private String quizId;
	@Column(name = "quizName")
	private String quizName;
	@Column(name = "quizCreatedDate")
	private LocalDate quizCreatedDate;
	@Column(name = "quizExpiredDate")
	private LocalDate quizExpiredDate;
	@Column(name = "isMandatory")
	private boolean isMandatory;
	
	private String quizDescription;
	private String quizNotice;
	private int quizTime;
	@ManyToOne
	@JoinColumn(name = "techStackId")
	private Techstack techstack;
	
	private Boolean openQuiz;
	private Boolean unAssignedQuiz;

	public Quiz() {

	}

	public String getQuizId() {
		return quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public LocalDate getQuizCreatedDate() {
		return quizCreatedDate;
	}

	public LocalDate getQuizExpiredDate() {
		return quizExpiredDate;
	}

	public Techstack getTechstack() {
		return techstack;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public void setQuizCreatedDate(LocalDate quizCreatedDate) {
		this.quizCreatedDate = quizCreatedDate;
	}

	public void setQuizExpiredDate(LocalDate quizExpiredDate) {
		this.quizExpiredDate = quizExpiredDate;
	}

	public void setTechstack(Techstack techstack) {
		this.techstack = techstack;
	}
}
