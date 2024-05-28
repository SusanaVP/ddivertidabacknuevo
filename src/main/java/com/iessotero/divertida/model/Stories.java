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
 * Representa un objeto de tipo Historia (Story) en la aplicación.
 */
@Entity
@Table(name = "stories")
public class Stories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String title;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoriesStory categoriesStory;

    /**
     * Constructor por defecto de la clase Stories.
     */
    public Stories() {
    }

    /**
     * Obtiene la descripción de la historia.
     * @return La descripción de la historia.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la historia.
     * @param description La descripción de la historia.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el título de la historia.
     * @return El título de la historia.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la historia.
     * @param title El título de la historia.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el identificador único de la historia.
     * @return El identificador único de la historia.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la historia.
     * @param id El identificador único de la historia.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la categoría a la que pertenece la historia.
     * @return La categoría a la que pertenece la historia.
     */
    public CategoriesStory getCategoriesStory() {
        return categoriesStory;
    }

    /**
     * Establece la categoría a la que pertenece la historia.
     * @param categoriesStory La categoría a la que pertenece la historia.
     */
    public void setCategoriesStory(CategoriesStory categoriesStory) {
        this.categoriesStory = categoriesStory;
    }

    @Override
    public String toString() {
        return "Stories [id=" + id + ", description=" + description + ", title=" + title + "]";
    }

}
