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

@Repository
public interface IVideoRepository extends JpaRepository<Videos, Long> {

	@Query("SELECT v FROM Videos v WHERE v.recommended = true")
	List<Videos> recommendedVideos();

	@Query("SELECT v FROM Videos v WHERE v.categoriesVideo.nameCategory  like %:category%")
	List<Videos> findVideosByCategory(@Param("category") String category);

	@Modifying
	@Transactional
	@Query("UPDATE Videos v SET v.recommended = true WHERE v.id = :idVideo")
	void addRecommendedVideo(@Param("idVideo")Long idVideo);

	@Modifying
	@Transactional
	@Query("UPDATE Videos v SET v.recommended = false WHERE v.id = :idVideo")
	void deleteRecommendedVideo(@Param("idVideo")Long idVideo);

	@Query("SELECT cv FROM CategoriesVideo cv ")
	List<CategoriesVideo> findAllCategoriesVideo();

}