package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Events;
import com.iessotero.divertida.services.EventsService;

@RestController
@RequestMapping("/events")
public class EventsController {

	@Autowired
	EventsService eventsService;

	@GetMapping()
	public List<Events> getEvents() {
		return eventsService.getAllEvents();
	}
}
