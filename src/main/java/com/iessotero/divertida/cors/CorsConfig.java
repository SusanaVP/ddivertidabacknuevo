package com.iessotero.divertida.cors;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOriginPatterns(Collections.singletonList("*"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
/*
 * UrlBasedCorsConfigurationSource source = new
 * UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
 * CorsConfiguration();
 * 
 * // Especifica el origen permitido (tu dominio)
 * config.setAllowedOrigins(Collections.singletonList("https://ddivertida.es"));
 * 
 * // Especifica las cabeceras permitidas
 * config.addAllowedHeader("Authorization");
 * config.addAllowedHeader("Content-Type");
 * 
 * // Especifica los métodos permitidos config.addAllowedMethod("GET");
 * config.addAllowedMethod("POST"); config.addAllowedMethod("PUT");
 * config.addAllowedMethod("DELETE");
 * 
 * source.registerCorsConfiguration("/**", config); return new
 * CorsFilter(source); } }
 */