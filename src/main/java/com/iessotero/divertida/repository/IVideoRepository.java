package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Videos;

import jakarta.transaction.Transactional;

@Repository
public interface IVideoRepository extends JpaRepository<Videos, Long> {

//	@Query("SELECT f.video FROM Favorites f WHERE f.userId = :idUser")
//	List<Videos> findFavoritesVideos(Long idUser);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO favorites (video_id, user_id) VALUES (:idVideo, :idUser)", nativeQuery = true)
	void addFavoriteVideo(Long idVideo, Long idUser);

	@Modifying
	@Transactional
	@Query(value = "UPDATE favorites SET ... WHERE video_id = :idVideo AND user_id = :idUser", nativeQuery = true)
	void updateFavoriteVideo(Long idVideo, Long idUser);

	@Query(value = "SELECT COUNT(*) FROM favorites WHERE video_id = :idVideo AND user_id = :idUser", nativeQuery = true)
	int existsFavoriteVideo(Long idVideo, Long idUser);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM favorites WHERE video_id = :idVideo AND user_id = :idUser", nativeQuery = true)
	void deleteFavoriteVideo(Long idVideo, Long idUser);

	@Query("SELECT v FROM Videos v WHERE v.recommended = true")
	List<Videos> recommendedVideos();

	@Query("SELECT v FROM Videos v WHERE v.categoriesVideo.nameCategory  like %:category%")
	List<Videos> findVideosByCategory(@Param("category") String category);

}