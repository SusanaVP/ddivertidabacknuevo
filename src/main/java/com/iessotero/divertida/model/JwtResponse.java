package com.iessotero.divertida.model;

/**
 * Esta clase representa un objeto de respuesta que contiene un token JWT.
 */
public class JwtResponse {

	private final String jwtToken;

	/**
	 * Constructor de la clase JwtResponse.
	 *
	 * @param jwtToken El token JWT generado.
	 */
	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	/**
	 * Obtiene el token JWT.
	 *
	 * @return El token JWT.
	 */
	public String getJwtToken() {
		return jwtToken;
	}

}
