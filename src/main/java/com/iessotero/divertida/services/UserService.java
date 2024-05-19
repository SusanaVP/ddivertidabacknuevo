package com.iessotero.divertida.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.User;
import com.iessotero.divertida.repository.IUserRepository;


@Service
public class UserService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private IUserRepository userRepository;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder; 

	public User saveUser(User user) {
		   user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.print(user.getPassword());
		return userRepository.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
//
//	public Long existsEmailPaswword(String email, String password) {
//		Optional<User> userOptional = userRepository.findByEmail(email);
//		if (userOptional.isPresent()
//				&& new BCryptPasswordEncoder().matches(password, userOptional.get().getPassword())) {
//			return userOptional.get().getId();
//		}
//		return null;
//	}

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	public String generateTokenForUser(User user) {
		return jwtService.generateToken(user, generateExtraClaims(user));
	}

	private Map<String, Object> generateExtraClaims(User user) {

		return Map.of("name", user.getEmail(), "isAdmin", user.getAdmin());

	}
}