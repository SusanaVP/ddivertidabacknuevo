package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.CategoriesVideo;
import com.iessotero.divertida.model.Videos;

import jakarta.transaction.Transactional;

/**
 * Repositorio para la entidad Videos, que proporciona métodos para acceder a la base de datos.
 */
@Repository
public interface IVideoRepository extends JpaRepository<Videos, Long> {

    /**
     * Obtiene una lista de videos recomendados.
     *
     * @return una lista de videos recomendados.
     */
    @Query("SELECT v FROM Videos v WHERE v.recommended = true")
    List<Videos> recommendedVideos();

    /**
     * Busca videos por categoría.
     *
     * @param category la categoría de los videos a buscar.
     * @return una lista de videos que pertenecen a la categoría especificada.
     */
    @Query("SELECT v FROM Videos v WHERE v.categoriesVideo.nameCategory  like %:category%")
    List<Videos> findVideosByCategory(@Param("category") String category);

    /**
     * Agrega un video a la lista de videos recomendados.
     *
     * @param idVideo el ID del video a agregar a los recomendados.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Videos v SET v.recommended = true WHERE v.id = :idVideo")
    void addRecommendedVideo(@Param("idVideo") Long idVideo);

    /**
     * Elimina un video de la lista de videos recomendados.
     *
     * @param idVideo el ID del video a eliminar de los recomendados.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Videos v SET v.recommended = false WHERE v.id = :idVideo")
    void deleteRecommendedVideo(@Param("idVideo") Long idVideo);

    /**
     * Obtiene todas las categorías de videos disponibles.
     *
     * @return una lista de todas las categorías de videos.
     */
    @Query("SELECT cv FROM CategoriesVideo cv")
    List<CategoriesVideo> findAllCategoriesVideo();
}
