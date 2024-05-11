package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.services.RiddlesService;

@RestController
@RequestMapping("/riddles")
public class RiddlesController {

	@Autowired
	RiddlesService riddlesService;

	@GetMapping()
	public List<Riddles> getRiddles() {
		return this.riddlesService.getAllRiddles();
	}

	@GetMapping("/riddlesByCategory/{categoryId}")
	public List<Riddles> getRiddlesByCategories(@PathVariable Long categoryId) {
		return this.riddlesService.getRiddlesById(categoryId);
	}
}
