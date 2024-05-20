package com.iessotero.divertida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.services.VideoService;

@RestController
@RequestMapping("/video")
public class VideoController {

	@Autowired
	VideoService videoService;

	@GetMapping()
	public List<Videos> getVideos() {
		return videoService.getAllVideos();
	}

	@GetMapping("/recommended")
	public List<Videos> getRecommendedVideos() {
		return this.videoService.recommendedVideos();
	}

	@GetMapping("/categories/{category}")
	public List<Videos> getVideosByCategories(@PathVariable String category) {
		return this.videoService.findVideosByCategory(category);
	}

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

}
