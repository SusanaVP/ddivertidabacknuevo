package com.iessotero.divertida.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

/**
 * Filtro para procesar las peticiones entrantes que contienen un token JWT de
 * autorización. Una vez que se recibe el token JWT, este filtro lo valida y
 * establece el contexto de seguridad con el usuario autenticado.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtService jwtService;

	/**
	 * Método para procesar internamente la petición HTTP y realizar la validación
	 * del token JWT. Si el token es válido, se establece el contexto de seguridad
	 * con el usuario autenticado.
	 *
	 * @param request     El objeto HttpServletRequest.
	 * @param response    El objeto HttpServletResponse.
	 * @param filterChain La cadena de filtros para continuar con el siguiente
	 *                    filtro.
	 * @throws ServletException Si ocurre una excepción durante el proceso del
	 *                          filtro.
	 * @throws IOException      Si ocurre una excepción de entrada o salida durante
	 *                          el proceso del filtro.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String authHeader = request.getHeader("Authorization");

			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}

			String jwt = authHeader.split(" ")[1];

			String userName = jwtService.extractUsername(jwt);

			User user = (User) customUserDetailsService.loadUserByUsername(userName);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName, null,
					user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authToken);

			filterChain.doFilter(request, response);

		} catch (ExpiredJwtException eje) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write("Token expirado");

		}
	}
}
