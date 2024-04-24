package com.iessotero.divertida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessotero.divertida.model.Favorites;

public interface IFavoriteRepository  extends JpaRepository<Favorites, Long> {

}
