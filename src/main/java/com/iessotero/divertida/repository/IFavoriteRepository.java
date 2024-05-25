package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Favorites;

import jakarta.transaction.Transactional;

/**
 * Repositorio para gestionar operaciones CRUD y consultas personalizadas para la entidad {@link Favorites}.
 */
@Repository
public interface IFavoriteRepository extends JpaRepository<Favorites, Long> {

    /**
     * Obtiene una lista de favoritos de un usuario dado su ID.
     *
     * @param idUser el ID del usuario.
     * @return una lista de objetos {@link Favorites}.
     */
    @Query("SELECT f FROM Favorites f WHERE f.user.id = :idUser")
    List<Favorites> findFavoritesIdUser(Long idUser);

    /**
     * Obtiene una lista de IDs de historias favoritas de un usuario dado su ID.
     *
     * @param idUser el ID del usuario.
     * @return una lista de IDs de historias favoritas.
     */
    @Query("SELECT contentId FROM Favorites f WHERE f.user.id = :idUser AND f.contentType = 'story'")
    List<Long> findIdStoriesFavorites(Long idUser);

    /**
     * Obtiene una lista de IDs de adivinanzas favoritos de un usuario dado su ID.
     *
     * @param idUser el ID del usuario.
     * @return una lista de IDs de adivinanzas favoritos.
     */
    @Query("SELECT contentId FROM Favorites f WHERE f.user.id = :idUser AND f.contentType = 'riddle'")
    List<Long> findIdRiddlesFavorites(Long idUser);

    /**
     * Obtiene una lista de IDs de videos favoritos de un usuario dado su ID.
     *
     * @param idUser el ID del usuario.
     * @return una lista de IDs de videos favoritos.
     */
    @Query("SELECT contentId FROM Favorites f WHERE f.user.id = :idUser AND f.contentType = 'video'")
    List<Long> findIdVideosFavorites(Long idUser);

    /**
     * Obtiene una lista de IDs de eventos favoritos de un usuario dado su ID.
     *
     * @param idUser el ID del usuario.
     * @return una lista de IDs de eventos favoritos.
     */
    @Query("SELECT contentId FROM Favorites f WHERE f.user.id = :idUser AND f.contentType = 'event'")
    List<Long> findIdEventsFavorites(Long idUser);

    /**
     * Elimina un contenido de los favoritos de un usuario.
     *
     * @param contentId el ID del contenido a eliminar.
     * @param userId el ID del usuario.
     * @param contentType el tipo de contenido a eliminar.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Favorites f WHERE f.contentId = :contentId AND f.user.id = :userId AND f.contentType = :contentType")
    void deleteFavorite(Long contentId, Long userId, String contentType);

    /**
     * Añade un contenido a los favoritos de un usuario.
     *
     * @param contentId el ID del contenido a añadir.
     * @param userId el ID del usuario.
     * @param contentType el tipo de contenido a añadir.
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO favorites (content_id, user_id, content_type) VALUES (:contentId, :userId, :contentType)", nativeQuery = true)
    void addFavorite(Long contentId, Long userId, String contentType);
}
