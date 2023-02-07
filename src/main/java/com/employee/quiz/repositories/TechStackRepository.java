package com.employee.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.quiz.domain.Techstack;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Repository
public interface TechStackRepository extends JpaRepository<Techstack, String> {
	
	 @Query(value = "SELECT tech_stack_id FROM techstack WHERE tech_stack_name =:tech_stack_name", nativeQuery = true)
	    String findIdByName(@Param("tech_stack_name") String techStackName);
}


