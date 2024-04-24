package com.iessotero.divertida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.repository.IFavoriteRepository;

@Service
public class FavoriteService {
	@Autowired
	private IFavoriteRepository favoriteRepository;

}
