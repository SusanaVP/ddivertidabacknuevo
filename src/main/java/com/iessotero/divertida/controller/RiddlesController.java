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

import com.iessotero.divertida.model.CategoriesRiddles;
import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.services.RiddlesService;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los adivinanzas.
 */
@RestController
@RequestMapping("/riddles")
public class RiddlesController {

    @Autowired
    RiddlesService riddlesService;

    /**
     * Obtiene la lista de todos los adivinanzas.
     *
     * @return una lista de objetos {@link Riddles}.
     */
    @GetMapping()
    public List<Riddles> getRiddles() {
        return this.riddlesService.getAllRiddles();
    }

    /**
     * Obtiene la lista de adivinanzas por categoría.
     *
     * @param categoryId el ID de la categoría.
     * @return una lista de objetos {@link Riddles}.
     */
    @GetMapping("/riddlesByCategory/{categoryId}")
    public List<Riddles> getRiddlesByCategories(@PathVariable Long categoryId) {
        return this.riddlesService.getRiddlesById(categoryId);
    }

    /**
     * Añade un nuevo adivinanza.
     *
     * @param riddle el objeto {@link Riddles} que se va a añadir.
     * @return una respuesta HTTP con el estado OK si se añade correctamente, o INTERNAL_SERVER_ERROR en caso de error.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addRiddle")
    public ResponseEntity<String> addRiddle(@RequestBody Riddles riddle) {
        try {
            this.riddlesService.addRiddle(riddle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un adivinanza dado su ID.
     *
     * @param idRiddle el ID del adivinanza a eliminar.
     * @return una respuesta HTTP con el estado OK si se elimina correctamente, o INTERNAL_SERVER_ERROR en caso de error.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteRiddle/{idRiddle}")
    public ResponseEntity<String> deleteRiddle(@PathVariable Long idRiddle) {
        try {
            this.riddlesService.deleteRiddle(idRiddle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene la lista de categorías de adivinanzas.
     *
     * @return una lista de objetos {@link CategoriesRiddles}.
     */
    @GetMapping("/riddlesCategories")
    public List<CategoriesRiddles> getRiddlesCategories() {
        return this.riddlesService.getRiddleCategories();
    }
}
