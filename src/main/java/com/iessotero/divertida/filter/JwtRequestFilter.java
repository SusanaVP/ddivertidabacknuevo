package com.iessotero.divertida.filter;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.iessotero.divertida.services.CustomUserDetailsService;
import com.iessotero.divertida.services.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

import org.slf4j.Logger;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtService jwtService;

	
//	
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
//
//	 @Override
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//	            throws ServletException, IOException {
//
//	        final String authorizationHeader = request.getHeader("Authorization");
//
//	        String username = null;
//	        String jwt = null;
//
//	        logger.info("Starting JwtRequestFilter for request URI: {}", request.getRequestURI());
//
//	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//	            jwt = authorizationHeader.substring(7);
//	            logger.info("JWT extracted from header: {}", jwt);
//	            username = jwtUtil.extractUsername(jwt);
//	            logger.info("Username extracted from JWT: {}", username);
//	        } else {
//	            logger.info("No JWT token found in the Authorization header or it does not start with Bearer");
//	        }
//
//	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
//	            logger.info("UserDetails loaded for username: {}", username);
//
//	            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
//	                logger.info("JWT token is valid");
//	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//	                        userDetails, null, userDetails.getAuthorities());
//	                usernamePasswordAuthenticationToken
//	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//	            } else {
//	                logger.warn("JWT token is not valid");
//	            }
//	        }
//
//	        chain.doFilter(request, response);
//	        logger.info("Completed JwtRequestFilter for request URI: {}", request.getRequestURI());
//	    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Obtener el header que contiente el jwt
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Obtener el jwt
            String jwt = authHeader.split(" ")[1];

            // Obener el userName del jwt
          String userName = jwtService.extractUsername(jwt);

            // Seteamos un objeto Authentication dentro del SecurityContext
            User user = (User) customUserDetailsService.loadUserByUsername(userName);


            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userName, null, user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);

            // Ejecutamos el filtro
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException eje) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("Token expirado");

        }
    }
}