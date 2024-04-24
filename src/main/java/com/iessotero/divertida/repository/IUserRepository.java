package com.iessotero.divertida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findByEmail(String email);

	@Query("SELECT u.id FROM User u WHERE u.email = :email AND u.password = :password")
	Long existsEmailPaswword(String email, String password);
}
