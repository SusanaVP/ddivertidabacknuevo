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
 * Representa una entrada de blog.
 */
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    private int likes;

    @Column
    private boolean validated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Constructor vacío de la clase Blog.
     */
    public Blog() {
    }

    /**
     * Obtiene el título del blog.
     * 
     * @return El título del blog.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del blog.
     * 
     * @param title El nuevo título del blog.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene la descripción del blog.
     * 
     * @return La descripción del blog.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del blog.
     * 
     * @param description La nueva descripción del blog.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la URL de la imagen asociada al blog.
     * 
     * @return La URL de la imagen del blog.
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece la URL de la imagen asociada al blog.
     * 
     * @param image La nueva URL de la imagen del blog.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Obtiene el número de "me gusta" del blog.
     * 
     * @return El número de "me gusta" del blog.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Establece el número de "me gusta" del blog.
     * 
     * @param likes El nuevo número de "me gusta" del blog.
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Obtiene el ID del blog.
     * 
     * @return El ID del blog.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del blog.
     * 
     * @param id El nuevo ID del blog.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario propietario del blog.
     * 
     * @return El usuario propietario del blog.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario propietario del blog.
     * 
     * @param user El nuevo usuario propietario del blog.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Verifica si el blog ha sido validado.
     * 
     * @return true si el blog ha sido validado, false de lo contrario.
     */
    public boolean getValidated() {
        return validated;
    }

    /**
     * Establece el estado de validación del blog.
     * 
     * @param validated El nuevo estado de validación del blog.
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
