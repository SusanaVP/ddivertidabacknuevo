package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Events;
import com.iessotero.divertida.model.Favorites;
import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.model.Stories;
import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.services.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deleteFavorite/{contentId}/{idUser}/{contentType}")
	public void deleteFavorite(@PathVariable Long contentId, @PathVariable Long idUser,
			@PathVariable String contentType) {
		this.favoriteService.deleteFavorite(contentId, idUser, contentType);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/addFavorite/{contentId}/{idUser}/{contentType}")
	public void addFavorite(@PathVariable Long contentId, @PathVariable Long idUser, @PathVariable String contentType) {
		this.favoriteService.addFavorite(contentId, idUser, contentType);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesIdUser/{idUser}")
	public List<Favorites> getFavoritesIdUser(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesIdUser(idUser);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesVideos/{idUser}")
	public List<Videos> getFavoritesVideos(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesVideos(idUser);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesStories/{idUser}")
	public List<Stories> getFavoritesStories(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesStories(idUser);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesRiddles/{idUser}")
	public List<Riddles> getFavoritesRiddles(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesRiddles(idUser);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesEvents/{idUser}")
	public List<Events> getFavoritesEvents(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesEvents(idUser);
	}
}
