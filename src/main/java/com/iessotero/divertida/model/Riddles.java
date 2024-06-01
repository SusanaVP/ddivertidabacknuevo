package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 * Esta clase representa una adivinanza.
 */
@Entity
@Table(name = "riddles")
public class Riddles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(length = 255)
	private String title;
	
	@Column(length = 255)
	private String solution;

	@ManyToOne()
	@JoinColumn(name = "category_id")
	private CategoriesRiddles categoriesRiddles;

	/**
	 * Constructor por defecto de la clase Riddles.
	 */
	public Riddles() {
	}

	/**
	 * Obtiene la descripción de la adivinanza.
	 *
	 * @return La descripción de la adivinanza.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establece la descripción de la adivinanza.
	 *
	 * @param description La nueva descripción de la adivinanza.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtiene el título de la adivinanza.
	 *
	 * @return El título de la adivinanza.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Establece el título de la adivinanza.
	 *
	 * @param title El nuevo título de la adivinanza.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtiene el identificador único de la adivinanza.
	 *
	 * @return El identificador único de la adivinanza.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único de la adivinanza.
	 *
	 * @param id El nuevo identificador único de la adivinanza.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene la categoría a la que pertenece la adivinanza.
	 *
	 * @return La categoría a la que pertenece la adivinanza.
	 */
	public CategoriesRiddles getCategoriesRiddles() {
		return categoriesRiddles;
	}

	/**
	 * Establece la categoría a la que pertenece la adivinanza.
	 *
	 * @param categoriesRiddles La nueva categoría a la que pertenece la adivinanza.
	 */
	public void setCategoriesRiddles(CategoriesRiddles categoriesRiddles) {
		this.categoriesRiddles = categoriesRiddles;
	}

	/**
	 * Obtiene la solución de la adivinanza.
	 *
	 * @return La solución de la adivinanza.
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * Establece la solución de la adivinanza.
	 *
	 * @param solution es la solución de la adivinanza.
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	
}
