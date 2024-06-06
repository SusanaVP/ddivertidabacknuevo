package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.CategoriesVideo;
import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.repository.IVideoRepository;

/**
 * Servicio para la gestión de videos.
 */
@Service
public class VideoService {

	@Autowired
	private IVideoRepository videoRepository;

	/**
	 * Obtiene todos los videos.
	 *
	 * @return una lista de todos los videos.
	 */
	public List<Videos> getAllVideos() {
		return videoRepository.findAll();
	}

	/**
	 * Obtiene una lista de videos recomendados.
	 *
	 * @return una lista de videos recomendados.
	 */
	public List<Videos> recommendedVideos() {
		return videoRepository.recommendedVideos();
	}

	/**
	 * Busca videos por categoría.
	 *
	 * @param category la categoría de los videos a buscar.
	 * @return una lista de videos que pertenecen a la categoría especificada.
	 */
	public List<Videos> findVideosByCategory(String category) {
		return videoRepository.findVideosByCategory(category);
	}

	/**
	 * Elimina un video de la lista de videos recomendados.
	 *
	 * @param idVideo el ID del video a eliminar de los recomendados.
	 */
	public void deleteRecommendedVideo(Long idVideo) {
		videoRepository.deleteRecommendedVideo(idVideo);
	}

	/**
	 * Agrega un video a la lista de videos recomendados.
	 *
	 * @param idVideo el ID del video a agregar a los recomendados.
	 */
	public void addRecommendedVideo(Long idVideo) {
		videoRepository.addRecommendedVideo(idVideo);
	}

	/**
	 * Guarda un nuevo video.
	 *
	 * @param video el video a guardar.
	 * @return el video guardado.
	 */
	public Videos saveVideo(Videos video) {
		return videoRepository.save(video);
	}

	/**
	 * Obtiene todas las categorías de videos disponibles.
	 *
	 * @return una lista de todas las categorías de videos.
	 */
	public List<CategoriesVideo> getVideoCategories() {
		return videoRepository.findAllCategoriesVideo();
	}

	/**
	 * Elimina un video por su ID.
	 *
	 * @param idVideo el ID del video a eliminar.
	 */
	public void deleteVideo(Long idVideo) {
		videoRepository.deleteById(idVideo);
	}
}
