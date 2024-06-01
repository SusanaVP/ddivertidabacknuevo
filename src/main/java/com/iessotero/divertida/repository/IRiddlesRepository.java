package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.CategoriesRiddles;
import com.iessotero.divertida.model.Riddles;

import jakarta.transaction.Transactional;

/**
 * Repositorio para gestionar operaciones CRUD y consultas personalizadas para
 * la entidad {@link Riddles}.
 */
@Repository
public interface IRiddlesRepository extends JpaRepository<Riddles, Long> {

	/**
	 * Busca adivinanzas por una lista de IDs.
	 *
	 * @param riddlesIds la lista de IDs de adivinanzas.
	 * @return una lista de objetos {@link Riddles}.
	 */
	@Query("SELECT r FROM Riddles r WHERE r.id IN :riddlesIds")
	List<Riddles> findRiddlesByIds(@Param("riddlesIds") List<Long> riddlesIds);

	/**
	 * Busca el ID de una categoría de adivinanzas por su nombre.
	 *
	 * @param category el nombre de la categoría.
	 * @return el ID de la categoría de adivinanzas.
	 */
	@Query("SELECT cr.id FROM CategoriesRiddles cr WHERE cr.nameCategory like %:category%")
	Long findCategoryRiddleId(@Param("category") String category);

	/**
	 * Busca adivinanzas por el ID de una categoría.
	 *
	 * @param categoryId el ID de la categoría de adivinanzas.
	 * @return una lista de objetos {@link Riddles}.
	 */
	@Query("SELECT r FROM Riddles r WHERE r.categoriesRiddles.id = :categoryId")
	List<Riddles> findByCategoriesRiddleId(@Param("categoryId") Long categoryId);

	/**
	 * Obtiene todas las categorías de adivinanzas.
	 *
	 * @return una lista de objetos {@link CategoriesRiddles}.
	 */
	@Query("SELECT cr FROM CategoriesRiddles cr ")
	List<CategoriesRiddles> findAllCategoriesRiddles();

	@Modifying
	@Transactional
	@Query("UPDATE Riddles r SET r.categoriesRiddles.id = :categoryId, r.title = :title, r.description = :description, r.solution = :solution WHERE r.id = :id")
	void updateRiddle(@Param("id") Long id, @Param("categoryId") Long categoryId, @Param("title") String title,
			@Param("description") String description, @Param("solution") String solution);
}
