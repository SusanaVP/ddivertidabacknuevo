package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Favorites;
import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.repository.IFavoriteRepository;
import com.iessotero.divertida.repository.IVideoRepository;

@Service
public class FavoriteService {
	@Autowired
	private IFavoriteRepository favoriteRepository;

	@Autowired
	private IVideoRepository videoRepository;

	public List<Favorites> getFavoritesIdUser(Long idUser) {
		return (List<Favorites>) favoriteRepository.findFavoritesIdUser(idUser);
	}
//
//	public void addFavoriteVideo(Long idVideo, Long idUser) {
//		int result = favoriteRepository.existsFavoriteVideo(idVideo, idUser);
//		if (result == 1) {
//			favoriteRepository.updateFavoriteVideo(idVideo, idUser);
//		} else {
//			favoriteRepository.addFavoriteVideo(idVideo, idUser);
//		}
//	}
//
//	public void deleteFavoriteVideos(Long idVideo, Long idUser) {
//		favoriteRepository.deleteFavoriteVideo(idVideo, idUser);
//	}

	public List<Videos> getFavoritesVideos(Long idUser) {
		List<Long> idVideos = findIdVideosFavorites(idUser);

		if (!idVideos.isEmpty()) {
			return videoRepository.findAllById(idVideos);
		}
		return null;
	}

	private List<Long> findIdVideosFavorites(Long idUser) {
		return favoriteRepository.findIdVideosFavorites(idUser);

	}

	public void addFavorite(Long contentId, Long idUser, String contentType) {
		favoriteRepository.addFavorite(contentId, idUser, contentType);
	}

	public void deleteFavorite(Long contentId, Long idUser, String contentType) {
		favoriteRepository.deleteFavorite(contentId, idUser, contentType);
	}

}
