package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Blog;
import com.iessotero.divertida.repository.IBlogRepository;

@Service
public class BlogService {

	@Autowired
	private IBlogRepository blogRepository;

	public List<Blog> getAllBlogs() {
		return (List<Blog>) blogRepository.findAllBlogs();
	}

	public void likesPlus(Long idBlog) {
		blogRepository.likesPlus(idBlog);
	}

	public void addBlog(String title, String description, int heart, Long idUser, String image, boolean validated) {
		blogRepository.saveBlog(title, description, heart, idUser, image, validated);
	}

	public List<Blog> getAllBlogsValidated() {
		return blogRepository.getAllBlogsValidated();
	}

	public List<Blog> getAllBlogsNoValidated() {
		return blogRepository.getAllBlogsNoValidated();
	}

	public void updateValidation(Long idBlog) {
		blogRepository.updateValidation(idBlog);
	}

	public void deleteBlog(Long idBlog) {
		blogRepository.deleteById(idBlog);
	}

}
