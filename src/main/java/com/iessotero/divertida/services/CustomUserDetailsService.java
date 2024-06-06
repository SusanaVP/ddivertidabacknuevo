package com.iessotero.divertida.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.User;
import com.iessotero.divertida.repository.IUserRepository;

/**
 * Servicio personalizado para cargar los detalles del usuario.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	/**
	 * Carga los detalles del usuario por su nombre de usuario (email).
	 *
	 * @param email, es el email del usuario.
	 * @return los detalles del usuario cargado.
	 * @throws UsernameNotFoundException si el usuario no se encuentra.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			return org.springframework.security.core.userdetails.User.withUsername(user.get().getEmail())
					.password(user.get().getPassword()).roles(user.get().getAdmin() ? "ADMIN" : "USER").build();
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}
}
