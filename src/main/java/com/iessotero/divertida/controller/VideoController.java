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

import com.iessotero.divertida.model.CategoriesVideo;
import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.services.VideoService;

/**
 * Controlador para gestionar las operaciones relacionadas con los videos.
 */
@RestController
@RequestMapping("/video")
public class VideoController {

	@Autowired
	VideoService videoService;

	/**
	 * Obtiene todos los videos disponibles.
	 *
	 * @return una lista de todos los videos disponibles.
	 */
	@GetMapping()
	public List<Videos> getVideos() {
		return videoService.getAllVideos();
	}

	/**
	 * Obtiene una lista de videos recomendados.
	 *
	 * @return una lista de videos recomendados.
	 */
	@GetMapping("/recommended")
	public List<Videos> getRecommendedVideos() {
		return this.videoService.recommendedVideos();
	}

	/**
	 * Obtiene una lista de videos por categoría.
	 *
	 * @param category la categoría de los videos a buscar.
	 * @return una lista de videos que pertenecen a la categoría especificada.
	 */
	@GetMapping("/categories/{category}")
	public List<Videos> getVideosByCategories(@PathVariable String category) {
		return this.videoService.findVideosByCategory(category);
	}

	/**
	 * Elimina un video recomendado.
	 *
	 * @param idVideo el ID del video a eliminar de los recomendados.
	 * @return ResponseEntity con el estado HTTP de la operación.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deleteRecommended/{idVideo}")
	public ResponseEntity<String> deleteRecommendedVideo(@PathVariable Long idVideo) {
		try {
			this.videoService.deleteRecommendedVideo(idVideo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Agrega un video a la lista de videos recomendados.
	 *
	 * @param idVideo el ID del video a agregar a los recomendados.
	 * @return ResponseEntity con el estado HTTP de la operación.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/addRecommended/{idVideo}")
	public ResponseEntity<String> addRecommendedVideo(@PathVariable Long idVideo) {
		try {
			this.videoService.addRecommendedVideo(idVideo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene las categorías de los videos.
	 *
	 * @return una lista de las categorías de los videos.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/videoCategories")
	public List<CategoriesVideo> getVideoCategories() {
		return this.videoService.getVideoCategories();
	}

	/**
	 * Agrega un nuevo video.
	 *
	 * @param video el video a agregar.
	 * @return ResponseEntity con el estado HTTP de la operación.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addVideo")
	public ResponseEntity<String> addVideo(@RequestBody Videos video) {
		Videos savedVideo = videoService.saveVideo(video);
		if (savedVideo != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Elimina un video.
	 *
	 * @param idVideo el ID del video a eliminar.
	 * @return ResponseEntity con el estado HTTP de la operación.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deleteVideo/{idVideo}")
	public ResponseEntity<String> deleteVideo(@PathVariable Long idVideo) {
		try {
			this.videoService.deleteVideo(idVideo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
