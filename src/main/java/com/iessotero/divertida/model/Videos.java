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
 * Representa un video en la aplicación.
 */
@Entity
@Table(name = "videos")
public class Videos {

    /** Identificador único del video. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descripción del video. */
    @Column(length = 255)
    private String description;

    /** Indica si el video es recomendado. */
    @Column
    private boolean recommended = false;

    /** Título del video. */
    @Column(length = 255)
    private String title;

    /** URL del video. */
    @Column(length = 255)
    private String url;

    /** Categoría del video. */
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoriesVideo categoriesVideo;

    /**
     * Constructor por defecto de la clase Videos.
     */
    public Videos() {
    }

    /**
     * Obtiene la descripción del video.
     * @return La descripción del video.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del video.
     * @param description La nueva descripción del video.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Indica si el video es recomendado.
     * @return true si el video es recomendado, false en caso contrario.
     */
    public boolean isRecommended() {
        return recommended;
    }

    /**
     * Establece si el video es recomendado.
     * @param recommended true si el video es recomendado, false en caso contrario.
     */
    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    /**
     * Obtiene el título del video.
     * @return El título del video.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del video.
     * @param title El nuevo título del video.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene la URL del video.
     * @return La URL del video.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL del video.
     * @param url La nueva URL del video.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtiene el identificador único del video.
     * @return El identificador único del video.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del video.
     * @param id El nuevo identificador único del video.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la categoría del video.
     * @return La categoría del video.
     */
    public CategoriesVideo getCategoriesVideo() {
        return categoriesVideo;
    }

    /**
     * Establece la categoría del video.
     * @param categoriesVideo La nueva categoría del video.
     */
    public void setCategoriesVideo(CategoriesVideo categoriesVideo) {
        this.categoriesVideo = categoriesVideo;
    }
}
