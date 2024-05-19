package com.iessotero.divertida.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.JwtResponse;
import com.iessotero.divertida.model.Login;
import com.iessotero.divertida.model.User;
import com.iessotero.divertida.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		if (savedUser != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findByEmail")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		Optional<User> userOptional = userService.findByEmail(email);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok().body(userOptional.get());
		} else {
			return ResponseEntity.ok().build();
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> login(@RequestBody @Valid Login authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		System.out.print(authenticationRequest);

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

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (Exception e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/getUser/{id}")
	public ResponseEntity<User> finduserById(@PathVariable Long id) {
		Optional<User> userOptional = userService.findUserById(id);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok().body(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/details")
	public UserDetails getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}
}