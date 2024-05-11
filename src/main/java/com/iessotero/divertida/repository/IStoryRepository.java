package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Stories;

@Repository
public interface IStoryRepository extends JpaRepository<Stories, Long> {

	/*@Query("SELECT scm.stories.id FROM StoryCategoriesMapping scm WHERE scm.categoriesStory.id IN :categoryIds")
	List<Long> findStoryIdsByCategoryIds(@Param("categoryIds") List<Long> categoryIds);*/

	@Query("SELECT s FROM Stories s WHERE s.id IN :storyIds")
	List<Stories> findStoriesByIds(@Param("storyIds") List<Long> storyIds);

	@Query("SELECT cs.id FROM CategoriesStory cs WHERE cs.nameCategory like %:category%")
	Long findCategoryStoryId(@Param("category") String category);

	
	@Query("SELECT s FROM Stories s WHERE s.categoriesStory.id = :categoryId")
	 List<Stories> findByCategoriesStoryId(@Param("categoryId") Long categoryId);
}
