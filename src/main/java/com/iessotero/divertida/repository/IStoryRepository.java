package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iessotero.divertida.model.Stories;

public interface IStoryRepository extends JpaRepository<Stories, Long> {

	/*@Query("SELECT c.id FROM CategoriesStory c WHERE " +
	        "(:category = 'classic' AND c.classic = true) OR " +
	        "(:category = 'short' AND c.shortStory = true) OR " +
	        "(:category = 'animal' AND c.animal = true) OR " +
	        "(:category = 'princess' AND c.princess = true) OR " +
	        "(:category = 'sleep' AND c.sleep = true) OR " +
	        "(:category = 'christmas' AND c.christmas = true)")
	List<Long> findCategoryIdsByCategory(@Param("category") String category);

	@Query("SELECT scm.stories.id FROM StoryCategoriesMapping scm WHERE scm.categoriesStory.id IN :categoryIds")
	List<Long> findStoryIdsByCategoryIds(@Param("categoryIds") List<Long> categoryIds);*/

	@Query("SELECT s FROM Stories s WHERE s.id IN :storyIds")
	List<Stories> findStoriesByIds(@Param("storyIds") List<Long> storyIds);

	@Query("SELECT cs.id FROM CategoriesStory cs WHERE cs.nameCategory like %:category%")
	Long findCategoryStoryId(@Param("category") String category);

	
	@Query("SELECT s FROM Stories s WHERE s.categoriesStory.id = :categoryId")
	 List<Stories> findByCategoriesStoryId(@Param("categoryId") Long categoryId);
}
