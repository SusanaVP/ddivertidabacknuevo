package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Blog;
import com.iessotero.divertida.services.BlogService;


@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	BlogService blogService;

	@GetMapping()
	public List<Blog> getBlog() {
		return blogService.getAllBlogs();
	}

	@GetMapping("/likesBlog/{idBlog}")
	public ResponseEntity<String> likesPlus(@PathVariable Long idBlog) {
		try {
			this.blogService.likesPlus(idBlog);
			return new ResponseEntity<>("Likes incrementados correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al incrementar los likes", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blogEntryData) {
		try {
			Long idUser = blogEntryData.getUser().getId();
			this.blogService.addBlog(blogEntryData.getTitle(), blogEntryData.getDescription(), blogEntryData.getLikes(),
					idUser, blogEntryData.getImage());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PostMapping("/uploadFile")
//	public String uploadFile(@RequestParam("file") MultipartFile file) {
//		// Obtiene la ubicación de la carpeta de almacenamiento
//		String uploadDir = fileStorageProperties.getUploadDir();
//
//		// Guarda la imagen en la carpeta de almacenamiento
//		try {
//			File destFile = new File(uploadDir + "/" + file.getOriginalFilename());
//			file.transferTo(destFile);
//			return "File uploaded successfully!";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "Failed to upload file.";
//		}
//	}
}
