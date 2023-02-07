package com.employee.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.quiz.domain.Quiz;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Repository
public interface QuizRepository extends CrudRepository<Quiz, String> {
	
	 @Query(value = "SELECT quiz_id FROM quiz WHERE quiz_name =:quiz_name", nativeQuery = true)
	    String findIdByName(@Param("quiz_name") String quizName);
}
