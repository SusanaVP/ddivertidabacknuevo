package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa un usuario en la aplicación.
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String name;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(length = 255)
	private String email;

	@Column
	private String date;

	@Column(length = 20)
	private String movil;

	@Column(length = 255)
	private String password;

	@Column(name = "postal_code", length = 255)
	private String postalCode;

	@Column
	private boolean admin;

	@Column
	private boolean emailValidated;

	/**
	 * Constructor por defecto de la clase User.
	 */
	public User() {
	}

	/**
	 * Obtiene el nombre del usuario.
	 * 
	 * @return El nombre del usuario.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del usuario.
	 * 
	 * @param name El nuevo nombre del usuario.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene el apellido del usuario.
	 * 
	 * @return El apellido del usuario.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Establece el apellido del usuario.
	 * 
	 * @param lastName El nuevo apellido del usuario.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 * 
	 * @return El correo electrónico del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 * 
	 * @param email El nuevo correo electrónico del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la fecha de registro del usuario.
	 * 
	 * @return La fecha de registro del usuario.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Establece la fecha de registro del usuario.
	 * 
	 * @param date La nueva fecha de registro del usuario.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Obtiene el número de teléfono móvil del usuario.
	 * 
	 * @return El número de teléfono móvil del usuario.
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * Establece el número de teléfono móvil del usuario.
	 * 
	 * @param movil El nuevo número de teléfono móvil del usuario.
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 * 
	 * @return La contraseña del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña del usuario.
	 * 
	 * @param password La nueva contraseña del usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene el código postal del usuario.
	 * 
	 * @return El código postal del usuario.
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Establece el código postal del usuario.
	 * 
	 * @param postalCode El nuevo código postal del usuario.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Indica si el usuario es administrador.
	 * 
	 * @return true si el usuario es administrador, false en caso contrario.
	 */
	public boolean getAdmin() {
		return admin;
	}

	/**
	 * Establece si el usuario es administrador.
	 * 
	 * @param admin true si el usuario es administrador, false en caso contrario.
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Obtiene el identificador único del usuario.
	 * 
	 * @return El identificador único del usuario.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único del usuario.
	 * 
	 * @param id El nuevo identificador único del usuario.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Indica si el email está validado.
	 * 
	 * @return true si el usuario está validado, false en caso contrario.
	 */
	public boolean getEmailValidated() {
		return emailValidated;
	}

	/**
	 * Establece si el email está validado o no
	 * 
	 * @param emailValidated true si el email está validado, false en caso
	 *                       contrario.
	 */
	public void setEmailValidated(boolean emailValidated) {
		this.emailValidated = emailValidated;
	}

}
