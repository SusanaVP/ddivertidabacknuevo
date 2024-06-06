package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa las categorías de los videos.
 */
@Entity
@Table(name = "categories_video")
public class CategoriesVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20)
	private String nameCategory;

	/**
	 * Constructor vacío de la clase CategoriesVideo.
	 */
	public CategoriesVideo() {
	}

	/**
	 * Obtiene el ID de la categoría de videos.
	 * 
	 * @return El ID de la categoría de videos.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID de la categoría de videos.
	 * 
	 * @param id El nuevo ID de la categoría de videos.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de la categoría de videos.
	 * 
	 * @return El nombre de la categoría de videos.
	 */
	public String getNameCategory() {
		return nameCategory;
	}

	/**
	 * Establece el nombre de la categoría de videos.
	 * 
	 * @param nameCategory El nuevo nombre de la categoría de videos.
	 */
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
}
