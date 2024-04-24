package com.iessotero.divertida.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Login;
import com.iessotero.divertida.model.User;
import com.iessotero.divertida.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/saveuser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		User savedUser = this.userService.saveUser(user);
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
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/login")
	public Long login(@RequestBody Login request) {
		return this.userService.existsEmailPaswword(request.getEmail(), request.getPassword());
	}

	@GetMapping("/getuser/{id}")
	public ResponseEntity<User> finduserById(@PathVariable Long id) {
		Optional<User> userOptional = this.userService.findUserById(id);

		if (userOptional.isPresent()) {
			return ResponseEntity.ok().body(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}