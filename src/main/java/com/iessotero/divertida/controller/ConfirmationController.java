package com.iessotero.divertida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iessotero.divertida.model.ConfirmationTokenEmail;
import com.iessotero.divertida.model.User;
import com.iessotero.divertida.services.TokenMgmtService;
import com.iessotero.divertida.services.UserService;

/**
 * Controlador para manejar la confirmación de correos electrónicos mediante
 * tokens de confirmación.
 */
@Controller
@RequestMapping("/email")
public class ConfirmationController {

	@Autowired
	private TokenMgmtService tokenMgmtService;

	@Autowired
	UserService userService;

	/**
	 * Maneja la confirmación del correo electrónico de un usuario usando un token
	 * de confirmación.
	 *
	 * @param token el token de confirmación proporcionado por el usuario
	 * @return "redirect:https://www.ddivertida.es/login" si la confirmación es
	 *         exitosa, de lo contrario, null si el token no es válido o ocurre
	 *         algún problema
	 */
	@GetMapping("/confirmation")
	public String confirmationPage(@RequestParam final String token) {

		ConfirmationTokenEmail confirmationTokenEmail = tokenMgmtService.findByToken(token);

		if (confirmationTokenEmail == null) {
			return null;
		}

		User user = confirmationTokenEmail.getUser();
		user.setEmailValidated(true);
		userService.saveUser(user);

		tokenMgmtService.deleteConfirmationToken(confirmationTokenEmail);

		return "redirect:https://www.ddivertida.es/login";
	}
}
