package com.employee.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.quiz.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	 @Query(value = "SELECT user_name FROM user WHERE hubble_id =:hubble_id", nativeQuery = true)
	    String findIdByName(@Param("hubble_id") int hubble_id);

}
