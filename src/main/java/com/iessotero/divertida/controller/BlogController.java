package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Blog;
import com.iessotero.divertida.services.BlogService;

/**
 * Controlador REST para gestionar operaciones relacionadas con blogs.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	BlogService blogService;

	/**
	 * Obtiene una lista de todos los blogs.
	 *
	 * @return una lista de objetos {@link Blog}.
	 */
	@GetMapping()
	public List<Blog> getBlog() {
		return blogService.getAllBlogs();
	}

	/**
	 * Obtiene una lista de blogs validados.
	 *
	 * @return una lista de objetos {@link Blog}.
	 */
	@GetMapping("/blogValidated")
	public List<Blog> getBlogValidated() {
		return blogService.getAllBlogsValidated();
	}

	/**
	 * Valida un blog dado su ID. Este método solo puede ser accedido por
	 * administradores.
	 *
	 * @param idBlog el ID del blog a validar.
	 * @return una respuesta con el estado de la validación.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editValidation/{idBlog}")
	public ResponseEntity<String> editValidation(@PathVariable Long idBlog) {
		try {
			this.blogService.updateValidation(idBlog);
			return new ResponseEntity<>("OK blog validation", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error blog validation", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene una lista de blogs no validados. Este método solo puede ser accedido
	 * por administradores.
	 *
	 * @return una lista de objetos {@link Blog}.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/blogNoValidated")
	public List<Blog> getBlogNoValidated() {
		return blogService.getAllBlogsNoValidated();
	}

	/**
	 * Incrementa los likes de un blog dado su ID. Este método puede ser accedido
	 * por cualquier usuario autenticado.
	 *
	 * @param idBlog el ID del blog al que se le incrementarán los likes.
	 * @return una respuesta con el estado de la operación.
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/likesBlog/{idBlog}")
	public ResponseEntity<String> likesPlus(@PathVariable Long idBlog) {
		try {
			this.blogService.likesPlus(idBlog);
			return new ResponseEntity<>("Likes incrementados correctamente", HttpStatus.OK);
		} catch (Exception e) {
			   System.out.println("Error al incrementar los likes");
			    e.printStackTrace();
			return new ResponseEntity<>("Error al incrementar los likes", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Añade un nuevo blog. Este método puede ser accedido por cualquier usuario
	 * autenticado.
	 *
	 * @param blogEntryData los datos del blog a añadir.
	 * @return una respuesta con el estado de la operación.
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blogEntryData) {
		try {
			Long idUser = blogEntryData.getUser().getId();
			this.blogService.addBlog(blogEntryData.getTitle(), blogEntryData.getDescription(), blogEntryData.getLikes(),
					idUser, blogEntryData.getImage(), blogEntryData.getValidated());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			   System.out.println("Error al añadir un nuevo blog:");
			    e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Elimina un blog dado su ID. Este método solo puede ser accedido por
	 * administradores.
	 *
	 * @param idBlog el ID del blog a eliminar.
	 * @return una respuesta con el estado de la operación.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deleteBlog/{idBlog}")
	public ResponseEntity<String> deleteBlog(@PathVariable Long idBlog) {
		try {
			this.blogService.deleteBlog(idBlog);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			   System.out.println("Error al eliminar el blog:");
			    e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
