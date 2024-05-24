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

import com.iessotero.divertida.model.CategoriesStory;
import com.iessotero.divertida.model.Stories;
import com.iessotero.divertida.services.StoryService;

@RestController
@RequestMapping("/story")
public class StoryController {

	@Autowired
	StoryService storyService;

	@GetMapping()
	public List<Stories> getStories() {
		return this.storyService.getAllStories();
	}

	@GetMapping("/storiesByCategory/{categoryId}")
	public List<Stories> getStoriesByCategories(@PathVariable Long categoryId) {
		return this.storyService.getStoryById(categoryId);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addStory")
	public ResponseEntity<String> addStory(@RequestBody Stories story) {
		try {
			this.storyService.addStory(story);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deleteStory/{idStory}")
	public ResponseEntity<String> deleteStory(@PathVariable Long idStory) {
		try {
			this.storyService.deleteStory(idStory);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/storyCategories")
	public List<CategoriesStory> getStoryCategories() {
		return this.storyService.getStoryCategories();
	}
}
