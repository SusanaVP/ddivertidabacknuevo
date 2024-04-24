package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Events;
import com.iessotero.divertida.repository.IEventsRepository;

@Service
public class EventsService {

	@Autowired
	private IEventsRepository eventsRepository;

	public List<Events> getAllEvents() {
		return (List<Events>) eventsRepository.findAllEvents();
	}
}
