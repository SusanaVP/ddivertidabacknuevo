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
 * Esta clase representa la entidad Favoritos, que almacena la relación entre un usuario y contenido marcado como favorito.
 */
@Entity
@Table(name = "favorites")
public class Favorites {

    /**
     * Identificador único de favorito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario al que pertenece el favorito.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Identificador del contenido marcado como favorito.
     */
    @Column(name = "content_id")
    private Long contentId;

    /**
     * Tipo de contenido marcado como favorito.
     */
    @Column
    private String contentType;

    /**
     * Constructor por defecto de la clase Favorites.
     */
    public Favorites() {

    }

    /**
     * Obtiene el identificador único del favorito.
     *
     * @return El identificador único del favorito.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del favorito.
     *
     * @param id El nuevo identificador único del favorito.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario al que pertenece el favorito.
     *
     * @return El usuario al que pertenece el favorito.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario al que pertenece el favorito.
     *
     * @param user El nuevo usuario al que pertenece el favorito.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene el identificador del contenido marcado como favorito.
     *
     * @return El identificador del contenido marcado como favorito.
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * Establece el identificador del contenido marcado como favorito.
     *
     * @param contentId El nuevo identificador del contenido marcado como favorito.
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * Obtiene el tipo de contenido marcado como favorito.
     *
     * @return El tipo de contenido marcado como favorito.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Establece el tipo de contenido marcado como favorito.
     *
     * @param contentType El nuevo tipo de contenido marcado como favorito.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
