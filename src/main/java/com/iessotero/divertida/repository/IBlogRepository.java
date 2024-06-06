package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Blog;

import jakarta.transaction.Transactional;

/**
 * Repositorio para gestionar operaciones CRUD y consultas personalizadas para
 * la entidad {@link Blog}.
 */
@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {

	/**
	 * Obtiene una lista de todos los blogs.
	 *
	 * @return una lista de objetos {@link Blog}.
	 */
	@Query("SELECT b FROM Blog b")
	List<Blog> findAllBlogs();

	/**
	 * Incrementa los likes de un blog dado su ID.
	 *
	 * @param idBlog el ID del blog al que se le incrementarán los likes.
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Blog b SET b.likes = b.likes + 1 WHERE b.id = :idBlog")
	void likesPlus(@Param("idBlog") Long idBlog);

	/**
	 * Guarda un nuevo blog con los detalles especificados.
	 *
	 * @param title       el título del blog.
	 * @param description la descripción del blog.
	 * @param likes       el número de "me gusta" del blog.
	 * @param idUser      el ID del usuario que añade el blog.
	 * @param image       la imagen del blog.
	 * @param validated   el estado de validación del blog.
	 */
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Blog (title, description, likes, user_id, image, validated) "
			+ "VALUES (:title, :description, :likes, :idUser, :image, :validated)", nativeQuery = true)
	void saveBlog(@Param("title") String title, @Param("description") String description, @Param("likes") int likes,
			@Param("idUser") Long idUser, @Param("image") String image, @Param("validated") boolean validated);

	/**
	 * Obtiene una lista de blogs validados.
	 *
	 * @return una lista de objetos {@link Blog}.
	 */
	@Query("SELECT b FROM Blog b WHERE b.validated = true")
	List<Blog> getAllBlogsValidated();

	/**
	 * Obtiene una lista de blogs no validados.
	 *
	 * @return una lista de objetos {@link Blog}.
	 */
	@Query("SELECT b FROM Blog b WHERE b.validated = false")
	List<Blog> getAllBlogsNoValidated();

	/**
	 * Actualiza el estado de validación de un blog dado su ID.
	 *
	 * @param idBlog el ID del blog a validar.
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Blog b SET b.validated = true WHERE b.id = :idBlog")
	void updateValidation(@Param("idBlog") Long idBlog);
}
