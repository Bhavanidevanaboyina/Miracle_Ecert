package com.employee.quiz.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import lombok.Data;

@Data
@Entity
@Table(name = "Techstack")
public class Techstack extends Auditable<String> {

	@Id
	@Column(name = "techStackId")
	private String techStackId;

	@Column(name = "techStackName")
	private String techStackName;

	/*@OneToMany(mappedBy = "techstack", cascade = CascadeType.ALL)
	private Set<Quiz> quizs;*/

	public Techstack() {

	}

	public String getTechStackId() {
		return techStackId;
	}

	public String getTechStackName() {
		return techStackName;
	}

	/*public Set<Quiz> getQuizs() {
		return quizs;
	}*/

	public void setTechStackId(String techStackId) {
		this.techStackId = techStackId;
	}

	public void setTechStackName(String techStackName) {
		this.techStackName = techStackName;
	}

	@Override
	public String toString() {
		return "[techStackId=" + techStackId + ", techStackName=" + techStackName + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", getTechStackId()=" + getTechStackId() + ", getTechStackName()="
				+ getTechStackName() + ", getCreatedBy()=" + getCreatedBy() + ", getCreationDate()=" + getCreationDate()
				+ ", getLastModifiedBy()=" + getLastModifiedBy() + ", getLastModifiedDate()=" + getLastModifiedDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				;
	}

	/*public void setQuizs(Set<Quiz> quizs) {
		this.quizs = quizs;
	}*/
}
