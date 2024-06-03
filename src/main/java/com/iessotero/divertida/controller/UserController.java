package com.iessotero.divertida.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.ConfirmationTokenEmail;
import com.iessotero.divertida.model.JwtResponse;
import com.iessotero.divertida.model.Login;
import com.iessotero.divertida.model.User;
import com.iessotero.divertida.services.EmailService;
import com.iessotero.divertida.services.TokenMgmtService;
import com.iessotero.divertida.services.UserService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

/**
 * Controlador para gestionar operaciones relacionadas con usuarios.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenMgmtService tokenMgmtService;

	@Autowired
	UserService userService;

	@Autowired
	private EmailService emailService;

	/** Dominio de la app */
	@Value("${app.domain}")
	private String domain;

	/**
	 * Endpoint para registrar un nuevo usuario y mandar el email para la
	 * confirmación.
	 *
	 * @param user el objeto {@link User} a registrar.
	 * @return un ResponseEntity con el estado HTTP correspondiente.
	 * @throws MessagingException
	 */
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws MessagingException {
		User savedUser = userService.saveUser(user);
		if (savedUser != null) {

			String token = tokenMgmtService.save(savedUser);

			StringBuilder builder = new StringBuilder();
			builder.append(
					"Haga clic en el siguiente enlace para confirmar su cuenta de usuario en la web D de Divertida: ");
			builder.append(domain);
			builder.append("/user/confirm?token=");
			builder.append(token);

			emailService.sendEmail(user.getEmail(), "Confirmación de cuenta de usuario", builder.toString());

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			System.out.println("Error al registrarse");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Busca un usuario por su dirección de correo electrónico.
	 *
	 * @param email la dirección de correo electrónico del usuario.
	 * @return un ResponseEntity con el usuario correspondiente o un estado HTTP
	 *         apropiado si no se encuentra.
	 */
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		Optional<User> userOptional = userService.findByEmail(email);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok().body(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Endpoint para iniciar sesión y generar un token JWT.
	 *
	 * @param authenticationRequest la solicitud de autenticación.
	 * @return un ResponseEntity con el token JWT generado.
	 * @throws Exception si las credenciales son inválidas.
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> login(@RequestBody @Valid Login authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> userOptional = userService.findByEmail(authenticationRequest.getEmail());

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String token = userService.generateTokenForUser(user);
			return ResponseEntity.ok(new JwtResponse(token));
		} else {
			throw new Exception("Invalid credentials");
		}
	}

	/**
	 * Autentica un usuario utilizando Spring Security.
	 *
	 * @param email    la dirección de correo electrónico del usuario.
	 * @param password la contraseña del usuario.
	 * @throws Exception si las credenciales son inválidas.
	 */
	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (Exception e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * Obtiene un usuario por su ID.
	 *
	 * @param id el ID del usuario.
	 * @return un ResponseEntity con el usuario correspondiente o un estado HTTP
	 *         apropiado si no se encuentra.
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/getUser/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		Optional<User> userOptional = userService.findUserById(id);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok().body(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Obtiene los detalles del usuario autenticado.
	 *
	 * @param userDetails los detalles del usuario autenticado.
	 * @return los detalles del usuario autenticado.
	 */
	@PostMapping("/details")
	public UserDetails getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}

	/**
	 * Maneja la confirmación del correo electrónico de un usuario usando un token de confirmación.
	 *
	 * @param token el token de confirmación proporcionado por el usuario
	 * @return un ResponseEntity<Void> que indica el resultado de la operación:
	 *         - 200 OK si el correo electrónico fue confirmado exitosamente
	 *         - 404 Not Found si el token es inválido o no se encuentra
	 */
	@GetMapping("/confirm")
	public ResponseEntity<Void> confirmEmail(@RequestParam final String token,Model model) {

		ConfirmationTokenEmail confirmationTokenEmail = tokenMgmtService.findByToken(token);

		if (confirmationTokenEmail == null) {

			return ResponseEntity.notFound().build();
		}

		User user = confirmationTokenEmail.getUser();
		user.setEmailValidated(true);

		userService.saveUser(user);

		 model.addAttribute("message", "Su cuenta ha sido confirmada exitosamente.Ya formas parte de la comunidad D de Divertida");
		// Eliminar token de confirmacion
		tokenMgmtService.deleteConfirmationToken(confirmationTokenEmail);

		return ResponseEntity.ok().build();
	}

}
