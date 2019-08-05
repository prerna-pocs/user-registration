package com.springboot.poc.userregistration.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.poc.userregistration.model.User;
@Repository
public interface UserRegistrationRepository extends JpaRepository<User, Long> {

	@Query("Select user FROM User user")
    List<User> findFirstAndLastName(Pageable pageable);
}
