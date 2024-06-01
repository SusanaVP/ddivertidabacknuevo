package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.CategoriesStory;
import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.model.Stories;
import com.iessotero.divertida.services.StoryService;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los cuentos.
 */
@RestController
@RequestMapping("/story")
public class StoryController {

    @Autowired
    StoryService storyService;

    /**
     * Obtiene la lista de todos los cuentos.
     *
     * @return una lista de objetos {@link Stories}.
     */
    @GetMapping()
    public List<Stories> getStories() {
        return this.storyService.getAllStories();
    }

    /**
     * Obtiene la lista de cuentos por ID de categoría.
     *
     * @param categoryId el ID de la categoría.
     * @return una lista de objetos {@link Stories}.
     */
    @GetMapping("/storiesByCategory/{categoryId}")
    public List<Stories> getStoriesByCategories(@PathVariable Long categoryId) {
        return this.storyService.getStoryById(categoryId);
    }

    /**
     * Añade un nuevo cuento.
     *
     * @param story el objeto {@link Stories} que se va a añadir.
     * @return una respuesta HTTP con el estado OK si se añade correctamente, o INTERNAL_SERVER_ERROR en caso de error.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addStory")
    public ResponseEntity<String> addStory(@RequestBody Stories story) {
        try {
            this.storyService.addStory(story);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un cuento dado su ID.
     *
     * @param idStory el ID del cuento a eliminar.
     * @return una respuesta HTTP con el estado OK si se elimina correctamente, o INTERNAL_SERVER_ERROR en caso de error.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteStory/{idStory}")
    public ResponseEntity<String> deleteStory(@PathVariable Long idStory) {
        try {
            this.storyService.deleteStory(idStory);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todas las categorías de cuentos.
     *
     * @return una lista de objetos {@link CategoriesStory}.
     */
    @GetMapping("/storyCategories")
    public List<CategoriesStory> getStoryCategories() {
        return this.storyService.getStoryCategories();
    }
    
	/**
	 * modifica un cuento.
	 *
	 * @param story el cuento {@link Riddles} que se va a modificar.
	 * @return una respuesta HTTP con el estado OK si se modifica correctamente, o
	 *         INTERNAL_SERVER_ERROR en caso de error.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editStory")
	public ResponseEntity<String> editRiddle(@RequestBody Stories story) {
		try {
			this.storyService.editStory(story);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
