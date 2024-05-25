package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.CategoriesRiddles;
import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.repository.IRiddlesRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los adivinanzas.
 */
@Service
public class RiddlesService {

    @Autowired
    private IRiddlesRepository riddlesRepository;

    /**
     * Obtiene la lista de todos los adivinanzas.
     *
     * @return una lista de objetos {@link Riddles}.
     */
    public List<Riddles> getAllRiddles() {
        return riddlesRepository.findAll();
    }

    /**
     * Obtiene la lista de adivinanzas por ID de categoría.
     *
     * @param categoryId el ID de la categoría.
     * @return una lista de objetos {@link Riddles}.
     */
    public List<Riddles> getRiddlesById(Long categoryId) {
        return riddlesRepository.findByCategoriesRiddleId(categoryId);
    }

    /**
     * Elimina una adivinanza dado su ID.
     *
     * @param idRiddle el ID de la adivinanza a eliminar.
     */
    public void deleteRiddle(Long idRiddle) {
        riddlesRepository.deleteById(idRiddle);
    }

    /**
     * Añade un nuevo adivinanza.
     *
     * @param riddle el objeto {@link Riddles} que se va a añadir.
     */
    public void addRiddle(Riddles riddle) {
        riddlesRepository.save(riddle);
    }

    /**
     * Obtiene todas las categorías de las adivinanzas.
     *
     * @return una lista de objetos {@link CategoriesRiddles}.
     */
    public List<CategoriesRiddles> getRiddleCategories() {
        return riddlesRepository.findAllCategoriesRiddles();
    }
}
