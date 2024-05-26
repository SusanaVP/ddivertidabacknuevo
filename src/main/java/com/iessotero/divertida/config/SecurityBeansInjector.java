package com.iessotero.divertida.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

import com.iessotero.divertida.services.CustomUserDetailsService;

/**
 * Configuración de autenticación para la aplicación.
 */
@Component
public class SecurityBeansInjector {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	/**
     * Define el bean para el administrador de autenticación.
     * 
     * @return El administrador de autenticación configurado.
     * @throws Exception Si ocurre un error al configurar el administrador de autenticación.
     */
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	  /**
     * Define el bean para el proveedor de autenticación.
     * 
     * @return El proveedor de autenticación configurado.
     */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

	   /**
     * Define el bean para el servicio de detalles de usuario.
     * 
     * @return El servicio de detalles de usuario configurado.
     */
	@Bean
	public UserDetailsService userDetailsService() {
		return email -> {
			return customUserDetailsService.loadUserByUsername(email);
		};
	}

	 /**
     * Define el bean para el codificador de contraseñas.
     * 
     * @return El codificador de contraseñas BCrypt configurado.
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}