package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.iessotero.divertida.model.Riddles;

@Repository
public interface IRiddlesRepository extends JpaRepository<Riddles, Long> {

	@Query("SELECT r FROM Riddles r WHERE r.id IN :riddlesIds")
	List<Riddles> findRiddlesByIds(@Param("riddlesIds") List<Long> ridllesIds);

	@Query("SELECT cr.id FROM CategoriesRiddles cr WHERE cr.nameCategory like %:category%")
	Long findCategoryRiddleId(@Param("category") String category);

	@Query("SELECT r FROM Riddles r WHERE r.categoriesRiddles.id = :categoryId")
	List<Riddles> findByCategoriesRiddleId(@Param("categoryId") Long categoryId);
}
