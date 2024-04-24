package com.iessotero.divertida.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.User;
import com.iessotero.divertida.repository.IUserRepository;;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Long existsEmailPaswword(String email, String password) {
		return userRepository.existsEmailPaswword(email, password);
	}

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}
}