package com.iessotero.divertida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.Events;

/**
 * Repositorio para gestionar operaciones CRUD y consultas personalizadas para
 * la entidad {@link Events}.
 */
@Repository
public interface IEventsRepository extends JpaRepository<Events, Long> {

	/**
	 * Obtiene una lista de todos los eventos.
	 *
	 * @return una lista de objetos {@link Events}.
	 */
	@Query("SELECT e FROM Events e")
	List<Events> findAllEvents();
}
