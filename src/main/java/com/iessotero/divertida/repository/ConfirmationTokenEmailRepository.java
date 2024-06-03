package com.iessotero.divertida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.ConfirmationTokenEmail;

@Repository
public interface ConfirmationTokenEmailRepository extends JpaRepository<ConfirmationTokenEmail, Long> {

	/**
	 * Buscar por token
	 * 
	 * @param token
	 * @return ConfirmationTokenEmail
	 */
	public ConfirmationTokenEmail findByToken(String token);
}
