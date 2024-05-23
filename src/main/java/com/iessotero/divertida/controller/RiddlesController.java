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
import com.iessotero.divertida.model.CategoriesStory;
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
	

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/addRiddle")
	public ResponseEntity<String> addRiddle(@RequestBody Riddles riddle) {
		try {
			this.riddlesService.addRiddle(riddle);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
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
	
	@GetMapping("/riddlesCategories")
	public List<CategoriesRiddles> getRiddlesCategories() {
		return this.riddlesService.getRiddleCategories();
	}
}
