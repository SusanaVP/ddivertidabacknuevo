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

	public List<Favorites> getFavoritesIdUser(Long idUser) {
		return (List<Favorites>) favoriteRepository.findFavoritesIdUser(idUser);
	}

	public List<Videos> getFavoritesVideos(Long idUser) {
		List<Long> idVideos = findIdVideosFavorites(idUser);

		if (!idVideos.isEmpty()) {
			return videoRepository.findAllById(idVideos);
		}
		return null;
	}

	public List<Stories> getFavoritesStories(Long idUser) {
		List<Long> idStories = findIdStoriesFavorites(idUser);

		if (!idStories.isEmpty()) {
			return storyRepository.findAllById(idStories);
		}
		return null;
	}

	public List<Riddles> getFavoritesRiddles(Long idUser) {
		List<Long> idRiddles = findIdRiddlesFavorites(idUser);

		if (!idRiddles.isEmpty()) {
			return riddlesRepository.findAllById(idRiddles);
		}
		return null;
	}

	public List<Events> getFavoritesEvents(Long idUser) {
		List<Long> idEvents = findIdEventsFavorites(idUser);

		if (!idEvents.isEmpty()) {
			return eventsRepository.findAllById(idEvents);
		}
		return null;
	}

	private List<Long> findIdVideosFavorites(Long idUser) {
		return favoriteRepository.findIdVideosFavorites(idUser);
	}

	private List<Long> findIdStoriesFavorites(Long idUser) {
		return favoriteRepository.findIdStoriesFavorites(idUser);
	}

	private List<Long> findIdRiddlesFavorites(Long idUser) {
		return favoriteRepository.findIdRiddlesFavorites(idUser);
	}

	private List<Long> findIdEventsFavorites(Long idUser) {
		return favoriteRepository.findIdEventsFavorites(idUser);
	}

	public void addFavorite(Long contentId, Long idUser, String contentType) {
		favoriteRepository.addFavorite(contentId, idUser, contentType);
	}

	public void deleteFavorite(Long contentId, Long idUser, String contentType) {
		favoriteRepository.deleteFavorite(contentId, idUser, contentType);
	}

}
