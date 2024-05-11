package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iessotero.divertida.model.Videos;
import com.iessotero.divertida.repository.IVideoRepository;

@Service
public class VideoService {

	@Autowired
	private IVideoRepository videoRepository;

	public List<Videos> getAllVideos() {
		return videoRepository.findAll();
	}

	public List<Videos> recommendedVideos() {
		return videoRepository.recommendedVideos();
	}

	public List<Videos> findVideosByCategory(String category) {
		return videoRepository.findVideosByCategory(category);
	}

}