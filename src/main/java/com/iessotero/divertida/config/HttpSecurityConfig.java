package com.iessotero.divertida.config;

import com.iessotero.divertida.filter.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    /**
     * Configura la cadena de filtros de seguridad de la aplicación.
     * 
     * Este método define una configuración de seguridad específica para la aplicación,
     * incluyendo la deshabilitación de la protección CSRF, la configuración de la gestión
     * de sesiones para ser sin estado (stateless), la adición de un filtro JWT antes del
     * filtro de autenticación de nombre de usuario y contraseña, y la configuración del
     * proveedor de autenticación.
     *
     * @param httpSecurity El objeto {@link HttpSecurity} que se utiliza para construir
     *                     la configuración de seguridad.
     * @return Un objeto {@link SecurityFilterChain} que representa la cadena de filtros
     *         de seguridad configurada.
     * @throws Exception Si ocurre un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);

        return httpSecurity.build();
    }

}