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

/**
 * Controlador REST para gestionar operaciones relacionadas con los favoritos de
 * los usuarios.
 */
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;

	/**
	 * Elimina un contenido de los favoritos de un usuario. Este método puede ser
	 * accedido por cualquier usuario autenticado.
	 *
	 * @param contentId   el ID del contenido a eliminar de los favoritos.
	 * @param idUser      el ID del usuario.
	 * @param contentType el tipo de contenido a eliminar (video, historia,
	 *                    acertijo, evento).
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deleteFavorite/{contentId}/{idUser}/{contentType}")
	public void deleteFavorite(@PathVariable Long contentId, @PathVariable Long idUser,
			@PathVariable String contentType) {
		this.favoriteService.deleteFavorite(contentId, idUser, contentType);
	}

	/**
	 * Añade un contenido a los favoritos de un usuario. Este método puede ser
	 * accedido por cualquier usuario autenticado.
	 *
	 * @param contentId   el ID del contenido a añadir a los favoritos.
	 * @param idUser      el ID del usuario.
	 * @param contentType el tipo de contenido a añadir (video, historia, acertijo,
	 *                    evento).
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/addFavorite/{contentId}/{idUser}/{contentType}")
	public void addFavorite(@PathVariable Long contentId, @PathVariable Long idUser, @PathVariable String contentType) {
		this.favoriteService.addFavorite(contentId, idUser, contentType);
	}

	/**
	 * Obtiene la lista de favoritos de un usuario. Este método puede ser accedido
	 * por cualquier usuario autenticado.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Favorites}.
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesIdUser/{idUser}")
	public List<Favorites> getFavoritesIdUser(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesIdUser(idUser);
	}

	/**
	 * Obtiene la lista de videos favoritos de un usuario. Este método puede ser
	 * accedido por cualquier usuario autenticado.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Videos}.
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesVideos/{idUser}")
	public List<Videos> getFavoritesVideos(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesVideos(idUser);
	}

	/**
	 * Obtiene la lista de historias favoritas de un usuario. Este método puede ser
	 * accedido por cualquier usuario autenticado.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Stories}.
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesStories/{idUser}")
	public List<Stories> getFavoritesStories(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesStories(idUser);
	}

	/**
	 * Obtiene la lista de adivinanzas favoritos de un usuario. Este método puede
	 * ser accedido por cualquier usuario autenticado.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Riddles}.
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesRiddles/{idUser}")
	public List<Riddles> getFavoritesRiddles(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesRiddles(idUser);
	}

	/**
	 * Obtiene la lista de eventos favoritos de un usuario. Este método puede ser
	 * accedido por cualquier usuario autenticado.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Events}.
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/favoritesEvents/{idUser}")
	public List<Events> getFavoritesEvents(@PathVariable Long idUser) {
		return this.favoriteService.getFavoritesEvents(idUser);
	}
}
