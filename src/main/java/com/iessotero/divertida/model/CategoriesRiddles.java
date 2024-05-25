package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa las categorías de las adivinanzas.
 */
@Entity
@Table(name = "categories_riddles")
public class CategoriesRiddles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String nameCategory;

    /**
     * Constructor vacío de la clase CategoriesRiddles.
     */
    public CategoriesRiddles() {
    }

    /**
     * Obtiene el ID de la categoría de adivinanzas.
     * 
     * @return El ID de la categoría de adivinanzas.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la categoría de adivinanzas.
     * 
     * @param id El nuevo ID de la categoría de adivinanzas.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría de adivinanzas.
     * 
     * @return El nombre de la categoría de adivinanzas.
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * Establece el nombre de la categoría de adivinanzas.
     * 
     * @param nameCategory El nuevo nombre de la categoría de adivinanzas.
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
