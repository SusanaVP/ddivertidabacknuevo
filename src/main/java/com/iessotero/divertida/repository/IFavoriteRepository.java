package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Favorites;
import com.iessotero.divertida.model.Videos;

import jakarta.transaction.Transactional;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorites, Long> {

	@Query("SELECT f FROM Favorites f WHERE f.user.id = :idUser")
	List<Favorites> findFavoritesIdUser(Long idUser);

	@Query("SELECT contentId FROM Favorites f WHERE f.user.id = :idUser AND f.contentType = 'video'")
	List<Long> findIdVideosFavorites(Long idUser);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Favorites f WHERE f.contentId = :contentId AND f.user.id = :userId AND f.contentType = :contentType")
	void deleteFavorite(Long contentId, Long userId, String contentType);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO favorites (content_id, user_id, content_type) VALUES (:contentId, :userId, :contentType)", nativeQuery = true)
	void addFavorite(Long contentId, Long userId, String contentType);

//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE favorites SET ... WHERE video_id = :idVideo AND user_id = :idUser", nativeQuery = true)
//	void updateFavoriteVideo(Long idVideo, Long idUser);
//
//	@Query(value = "SELECT COUNT(*) FROM favorites WHERE video_id = :idVideo AND user_id = :idUser", nativeQuery = true)
//	int existsFavoriteVideo(Long idVideo, Long idUser);
//
//	@Modifying
//	@Transactional
//	@Query(value = "DELETE FROM favorites WHERE video_id = :idVideo AND user_id = :idUser", nativeQuery = true)
//	void deleteFavoriteVideo(Long idVideo, Long idUser);
}
