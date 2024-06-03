package com.iessotero.divertida.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.ConfirmationTokenEmail;
import com.iessotero.divertida.model.User;
import com.iessotero.divertida.repository.ConfirmationTokenEmailRepository;

@Service
public class TokenMgmtService {

	@Autowired
	private ConfirmationTokenEmailRepository confirmationTokenEmailRepository;

	/**
     * Genera un token único.
     *
     * @return El token generado.
     */
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	/**
     * Guarda un token de confirmación de correo electrónico asociado a un usuario.
     *
     * @param user El usuario asociado al token.
     * @return El token generado.
     */
	public String save(User user) {
		String token = generateToken();
		confirmationTokenEmailRepository.save(new ConfirmationTokenEmail(user, token));

		return token;
	}

	 /**
     * Busca un token de confirmación de correo electrónico por su valor.
     *
     * @param token El valor del token a buscar.
     * @return El token de confirmación encontrado.
     */
	public ConfirmationTokenEmail findByToken(String token) {

		return confirmationTokenEmailRepository.findByToken(token);
	}

	/**
     * Elimina un token de confirmación de correo electrónico.
     *
     * @param confirmationTokenEmail El token de confirmación a eliminar.
     */
	public void deleteConfirmationToken(ConfirmationTokenEmail confirmationTokenEmail) {

		confirmationTokenEmailRepository.deleteById(confirmationTokenEmail.getId());
	}
}
