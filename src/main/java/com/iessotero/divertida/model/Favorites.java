package com.iessotero.divertida.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorites {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Favorites() {
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "video_id")
	private Videos video;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_id")
	private Events event;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "story_id")
	private Stories story;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "riddles_id")
	private Riddles riddles;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}