package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Blog;

import jakarta.transaction.Transactional;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {

	@Query("SELECT b FROM Blog b")
	List<Blog> findAllBlogs();

	@Modifying
	@Transactional
	@Query("UPDATE Blog b SET b.likes = b.likes + 1 WHERE b.id = :idBlog")
	void likesPlus(Long idBlog);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Blog (title, description, likes, user_id, image) "
			+ "VALUES (:title, :description, :likes, :idUser, :image)", nativeQuery = true)
	void saveBlog(@Param("title") String title, @Param("description") String description, @Param("likes") int likes,
			@Param("idUser") Long idUser, @Param("image") String image);
}