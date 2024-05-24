package com.neo.hello.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neo.hello.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u from User u")
	Page<User> findList(Pageable pageable);
	
	User findByUserName(String userName);
}
