package com.iessotero.divertida.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.ConfirmationTokenEmail;
import com.iessotero.divertida.model.User;
import com.iessotero.divertida.repository.ConfirmationTokenEmailRepository;

@Service
public class TokenMgmtService {

	/** Dependencia ConfirmationTokenRepositoryI */
	@Autowired
	private ConfirmationTokenEmailRepository tokenRepository;

	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	public String save(User user) {
		// Generar token de confirmacion
		String token = generateToken();

		// Guardar
		tokenRepository.save(new ConfirmationTokenEmail(user, token));

		return token;
	}

	public ConfirmationTokenEmail findByToken(String token) {

		return tokenRepository.findByToken(token);
	}

	public void deleteConfirmationToken(ConfirmationTokenEmail confirmationToken) {

		// Eliminar por Id
		tokenRepository.deleteById(confirmationToken.getConfirmId());
	}
}
