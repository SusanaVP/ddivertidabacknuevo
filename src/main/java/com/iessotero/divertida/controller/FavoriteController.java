package com.iessotero.divertida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessotero.divertida.services.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;
}
