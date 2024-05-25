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

import com.iessotero.divertida.model.Events;
import com.iessotero.divertida.services.EventsService;

/**
 * Controlador REST para gestionar operaciones relacionadas con eventos.
 */
@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    EventsService eventsService;

    /**
     * Obtiene una lista de todos los eventos.
     *
     * @return una lista de objetos {@link Events}.
     */
    @GetMapping()
    public List<Events> getEvents() {
        return eventsService.getAllEvents();
    }

    /**
     * Añade un nuevo evento. Este método puede ser accedido por cualquier usuario autenticado.
     *
     * @param event los datos del evento a añadir.
     * @return una respuesta con el estado de la operación.
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody Events event) {
        try {
            this.eventsService.addEvent(event);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un evento dado su ID. Este método solo puede ser accedido por administradores.
     *
     * @param idEvent el ID del evento a eliminar.
     * @return una respuesta con el estado de la operación.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteEvent/{idEvent}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long idEvent) {
        try {
            this.eventsService.deleteEvent(idEvent);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
