package com.iessotero.divertida.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ConfirmationTokenEmail {

	/** Identificador (PK) */
	@Id
	@Column(name = "C_CONFIRM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long confirmId;

	/** Token */
	@Column(name = "C_TOKEN", length = 100)
	private String token;

	/** Usuario */
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

	public Long getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(Long confirmId) {
		this.confirmId = confirmId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
