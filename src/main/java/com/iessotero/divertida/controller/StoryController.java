package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
