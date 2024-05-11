package com.iessotero.divertida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.Riddles;
import com.iessotero.divertida.repository.IRiddlesRepository;

@Service
public class RiddlesService {

	@Autowired
	private IRiddlesRepository riddlesRepository;

	public List<Riddles> getAllRiddles() {
		return riddlesRepository.findAll();
	}

	public List<Riddles> getRiddlesById(Long categoryId) {
		return riddlesRepository.findByCategoriesRiddleId(categoryId);
	}
}
