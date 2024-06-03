package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "confirmation_token_email")
public class ConfirmationTokenEmail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "C_TOKEN", length = 100)
	private String token;

	@OneToOne(targetEntity = User.class)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	/**
	 * Constructor
	 * 
	 * @param user
	 * @param token
	 */
	public ConfirmationTokenEmail(User user, String token) {
		this.user = user;
		this.token = token;
	}

	/**
	 * Constructor
	 * 
	 */
	public ConfirmationTokenEmail() {

	}

	/**
	 * Obtiene el ID de confirmación.
	 *
	 * @return el ID de confirmación
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID de confirmación.
	 *
	 * @param id el nuevo ID de confirmación
	 */
	public void setConfirmId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el token.
	 *
	 * @return el token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Establece el token.
	 *
	 * @param token el nuevo token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Obtiene el usuario asociado.
	 *
	 * @return el usuario asociado
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Establece el usuario asociado.
	 *
	 * @param user el nuevo usuario asociado
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
