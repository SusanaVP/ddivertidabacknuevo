package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.CategoriesStory;
import com.iessotero.divertida.model.Stories;

/**
 * Repositorio para gestionar operaciones CRUD y consultas personalizadas para la entidad {@link Stories}.
 */
@Repository
public interface IStoryRepository extends JpaRepository<Stories, Long> {

    /**
     * Busca cuentos por una lista de IDs.
     *
     * @param storyIds la lista de IDs de cuentos.
     * @return una lista de objetos {@link Stories}.
     */
    @Query("SELECT s FROM Stories s WHERE s.id IN :storyIds")
    List<Stories> findStoriesByIds(@Param("storyIds") List<Long> storyIds);

    /**
     * Busca el ID de una categoría de cuentos por su nombre.
     *
     * @param category el nombre de la categoría.
     * @return el ID de la categoría de cuentos.
     */
    @Query("SELECT cs.id FROM CategoriesStory cs WHERE cs.nameCategory like %:category%")
    Long findCategoryStoryId(@Param("category") String category);

    /**
     * Busca cuentos por el ID de una categoría.
     *
     * @param categoryId el ID de la categoría de cuentos.
     * @return una lista de objetos {@link Stories}.
     */
    @Query("SELECT s FROM Stories s WHERE s.categoriesStory.id = :categoryId")
    List<Stories> findByCategoriesStoryId(@Param("categoryId") Long categoryId);

    /**
     * Obtiene todas las categorías de cuentos.
     *
     * @return una lista de objetos {@link CategoriesStory}.
     */
    @Query("SELECT cs FROM CategoriesStory cs ")
    List<CategoriesStory> findAllCategoriesStories();
}
