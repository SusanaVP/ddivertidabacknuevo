package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa las categorías de los cuentos.
 */
@Entity
@Table(name = "categories_story")
public class CategoriesStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String nameCategory;

    /**
     * Constructor vacío de la clase CategoriesStory.
     */
    public CategoriesStory() {
    }

    /**
     * Obtiene el ID de la categoría de cuentos.
     * 
     * @return El ID de la categoría de cuentos.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la categoría de cuentos.
     * 
     * @param id El nuevo ID de la categoría de cuentos.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría de cuentos.
     * 
     * @return El nombre de la categoría de cuentos.
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * Establece el nombre de la categoría de cuentos.
     * 
     * @param nameCategory El nuevo nombre de la categoría de cuentos.
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
