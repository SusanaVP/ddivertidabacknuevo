package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Events;
import com.iessotero.divertida.repository.IEventsRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los eventos.
 */
@Service
public class EventsService {

	@Autowired
	private IEventsRepository eventsRepository;

	/**
	 * Obtiene una lista de todos los eventos.
	 *
	 * @return una lista de objetos {@link Events}.
	 */
	public List<Events> getAllEvents() {
		return (List<Events>) eventsRepository.findAllEvents();
	}

	/**
	 * Añade un nuevo evento.
	 *
	 * @param event los datos del evento a añadir.
	 */
	public void addEvent(Events event) {
		eventsRepository.save(event);
	}

	/**
	 * Elimina un evento dado su ID.
	 *
	 * @param idEvent el ID del evento a eliminar.
	 */
	public void deleteEvent(Long idEvent) {
		eventsRepository.deleteById(idEvent);
	}
}
