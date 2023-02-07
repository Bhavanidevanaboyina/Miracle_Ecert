package com.employee.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.quiz.domain.QuestionPaper;

@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Integer>{

}
