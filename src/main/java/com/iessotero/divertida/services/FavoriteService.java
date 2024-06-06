package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Events;
import com.iessotero.divertida.model.Favorites;
import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.model.Stories;
import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.repository.IEventsRepository;
import com.iessotero.divertida.repository.IFavoriteRepository;
import com.iessotero.divertida.repository.IRiddlesRepository;
import com.iessotero.divertida.repository.IStoryRepository;
import com.iessotero.divertida.repository.IVideoRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los favoritos.
 */
@Service
public class FavoriteService {

	@Autowired
	private IFavoriteRepository favoriteRepository;

	@Autowired
	private IVideoRepository videoRepository;

	@Autowired
	private IStoryRepository storyRepository;

	@Autowired
	private IRiddlesRepository riddlesRepository;

	@Autowired
	private IEventsRepository eventsRepository;

	/**
	 * Obtiene la lista de favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Favorites}.
	 */
	public List<Favorites> getFavoritesIdUser(Long idUser) {
		return favoriteRepository.findFavoritesIdUser(idUser);
	}

	/**
	 * Obtiene la lista de videos favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Videos} o null si no hay favoritos.
	 */
	public List<Videos> getFavoritesVideos(Long idUser) {
		List<Long> idVideos = findIdVideosFavorites(idUser);

		if (!idVideos.isEmpty()) {
			return videoRepository.findAllById(idVideos);
		}
		return null;
	}

	/**
	 * Obtiene la lista de historias favoritas de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Stories} o null si no hay favoritos.
	 */
	public List<Stories> getFavoritesStories(Long idUser) {
		List<Long> idStories = findIdStoriesFavorites(idUser);

		if (!idStories.isEmpty()) {
			return storyRepository.findAllById(idStories);
		}
		return null;
	}

	/**
	 * Obtiene la lista de adivinanzas favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Riddles} o null si no hay favoritos.
	 */
	public List<Riddles> getFavoritesRiddles(Long idUser) {
		List<Long> idRiddles = findIdRiddlesFavorites(idUser);

		if (!idRiddles.isEmpty()) {
			return riddlesRepository.findAllById(idRiddles);
		}
		return null;
	}

	/**
	 * Obtiene la lista de eventos favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de objetos {@link Events} o null si no hay favoritos.
	 */
	public List<Events> getFavoritesEvents(Long idUser) {
		List<Long> idEvents = findIdEventsFavorites(idUser);

		if (!idEvents.isEmpty()) {
			return eventsRepository.findAllById(idEvents);
		}
		return null;
	}

	/**
	 * Obtiene una lista de IDs de videos favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de IDs de videos favoritos.
	 */
	private List<Long> findIdVideosFavorites(Long idUser) {
		return favoriteRepository.findIdVideosFavorites(idUser);
	}

	/**
	 * Obtiene una lista de IDs de historias favoritas de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de IDs de historias favoritas.
	 */
	private List<Long> findIdStoriesFavorites(Long idUser) {
		return favoriteRepository.findIdStoriesFavorites(idUser);
	}

	/**
	 * Obtiene una lista de IDs de adivinanzas favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de IDs de adivinanzas favoritos.
	 */
	private List<Long> findIdRiddlesFavorites(Long idUser) {
		return favoriteRepository.findIdRiddlesFavorites(idUser);
	}

	/**
	 * Obtiene una lista de IDs de eventos favoritos de un usuario dado su ID.
	 *
	 * @param idUser el ID del usuario.
	 * @return una lista de IDs de eventos favoritos.
	 */
	private List<Long> findIdEventsFavorites(Long idUser) {
		return favoriteRepository.findIdEventsFavorites(idUser);
	}

	/**
	 * Añade un contenido a los favoritos de un usuario.
	 *
	 * @param contentId   el ID del contenido a añadir.
	 * @param idUser      el ID del usuario.
	 * @param contentType el tipo de contenido a añadir.
	 */
	public void addFavorite(Long contentId, Long idUser, String contentType) {
		favoriteRepository.addFavorite(contentId, idUser, contentType);
	}

	/**
	 * Elimina un contenido de los favoritos de un usuario.
	 *
	 * @param contentId   el ID del contenido a eliminar.
	 * @param idUser      el ID del usuario.
	 * @param contentType el tipo de contenido a eliminar.
	 */
	public void deleteFavorite(Long contentId, Long idUser, String contentType) {
		favoriteRepository.deleteFavorite(contentId, idUser, contentType);
	}

}
