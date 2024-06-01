package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.CategoriesStory;
import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.model.Stories;
import com.iessotero.divertida.repository.IStoryRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los cuentos.
 */
@Service
public class StoryService {

    @Autowired
    private IStoryRepository storyRepository;

    /**
     * Obtiene la lista de todos los cuentos.
     *
     * @return una lista de objetos {@link Stories}.
     */
    public List<Stories> getAllStories() {
        return storyRepository.findAll();
    }

    /**
     * Obtiene la lista de cuentos por ID de categoría.
     *
     * @param categoryId el ID de la categoría.
     * @return una lista de objetos {@link Stories}.
     */
    public List<Stories> getStoryById(Long categoryId) {
        return storyRepository.findByCategoriesStoryId(categoryId);
    }

    /**
     * Añade un nuevo cuento.
     *
     * @param story el objeto {@link Stories} que se va a añadir.
     */
    public void addStory(Stories story) {
        storyRepository.save(story);	
    }

    /**
     * Elimina un cuento dado su ID.
     *
     * @param idStory el ID del cuento a eliminar.
     */
    public void deleteStory(Long idStory) {
        storyRepository.deleteById(idStory);
    }
    
    /**
     * Obtiene todas las categorías de cuentos.
     *
     * @return una lista de objetos {@link CategoriesStory}.
     */
    public List<CategoriesStory> getStoryCategories() {
        return storyRepository.findAllCategoriesStories();
    }
    
	
	/**
	 * Modifica un cuento.
	 *
	 * @param story el objeto {@link Riddles} que se va a modificar.
	 */
	public void editStory(Stories story) {
		storyRepository.updateStory(story.getId(), story.getCategoriesStory().getId(), story.getTitle(),
				story.getDescription());
	}

}
