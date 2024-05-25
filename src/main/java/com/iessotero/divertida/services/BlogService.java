package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Blog;
import com.iessotero.divertida.repository.IBlogRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los blogs.
 */
@Service
public class BlogService {

    @Autowired
    private IBlogRepository blogRepository;

    /**
     * Obtiene una lista de todos los blogs.
     *
     * @return una lista de objetos {@link Blog}.
     */
    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAllBlogs();
    }

    /**
     * Incrementa los likes de un blog dado su ID.
     *
     * @param idBlog el ID del blog al que se le incrementarán los likes.
     */
    public void likesPlus(Long idBlog) {
        blogRepository.likesPlus(idBlog);
    }

    /**
     * Añade un nuevo blog.
     *
     * @param title       el título del blog.
     * @param description la descripción del blog.
     * @param heart       el número de "me gusta" del blog.
     * @param idUser      el ID del usuario que añade el blog.
     * @param image       la imagen del blog.
     * @param validated   el estado de validación del blog.
     */
    public void addBlog(String title, String description, int heart, Long idUser, String image, boolean validated) {
        blogRepository.saveBlog(title, description, heart, idUser, image, validated);
    }

    /**
     * Obtiene una lista de blogs validados.
     *
     * @return una lista de objetos {@link Blog}.
     */
    public List<Blog> getAllBlogsValidated() {
        return blogRepository.getAllBlogsValidated();
    }

    /**
     * Obtiene una lista de blogs no validados.
     *
     * @return una lista de objetos {@link Blog}.
     */
    public List<Blog> getAllBlogsNoValidated() {
        return blogRepository.getAllBlogsNoValidated();
    }

    /**
     * Actualiza el estado de validación de un blog dado su ID.
     *
     * @param idBlog el ID del blog a validar.
     */
    public void updateValidation(Long idBlog) {
        blogRepository.updateValidation(idBlog);
    }

    /**
     * Elimina un blog dado su ID.
     *
     * @param idBlog el ID del blog a eliminar.
     */
    public void deleteBlog(Long idBlog) {
        blogRepository.deleteById(idBlog);
    }

}
