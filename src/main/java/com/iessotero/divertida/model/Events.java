package com.iessotero.divertida.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

/**
 * Representa eventos.
 */
@Entity
@Table(name = "events")
public class Events {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "TEXT")
	private String image;

	@Column(length = 255)
	private String info;

	@Column(length = 255)
	private String url;

	@Column
	private Date date;

	@Column
	private Float latitude;

	@Column
	private Float longitude;

	@Column
	private String town;

	@Column
	private String city;

	/**
	 * Constructor vacío de la clase Events.
	 */
	public Events() {
	}

	/**
	 * Obtiene el título del evento.
	 * 
	 * @return El título del evento.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Establece el título del evento.
	 * 
	 * @param title El nuevo título del evento.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtiene la descripción del evento.
	 * 
	 * @return La descripción del evento.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establece la descripción del evento.
	 * 
	 * @param description La nueva descripción del evento.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtiene la imagen del evento.
	 * 
	 * @return La imagen del evento.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Establece la imagen del evento.
	 * 
	 * @param image La nueva imagen del evento.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Obtiene la información adicional del evento.
	 * 
	 * @return La información adicional del evento.
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Establece la información adicional del evento.
	 * 
	 * @param info La nueva información adicional del evento.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Obtiene la URL relacionada con el evento.
	 * 
	 * @return La URL relacionada con el evento.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Establece la URL relacionada con el evento.
	 * 
	 * @param url La nueva URL relacionada con el evento.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtiene la fecha del evento.
	 * 
	 * @return La fecha del evento.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Establece la fecha del evento.
	 * 
	 * @param date La nueva fecha del evento.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Obtiene la latitud del evento.
	 * 
	 * @return La latitud del evento.
	 */
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * Establece la latitud del evento.
	 * 
	 * @param latitude La nueva latitud del evento.
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Obtiene la longitud del evento.
	 * 
	 * @return La longitud del evento.
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * Establece la longitud del evento.
	 * 
	 * @param longitude La nueva longitud del evento.
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * Obtiene el ID del evento.
	 * 
	 * @return El ID del evento.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID del evento.
	 * 
	 * @param id El nuevo ID del evento.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del municipio asociado al evento.
	 *
	 * @return El nombre del municipio asociado al evento.
	 */
	public String getTown() {
		return town;
	}

	/**
	 * Establece el nombre del municipio asociado al evento.
	 *
	 * @param town El nuevo nombre del municipio asociado al evento.
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * Obtiene el nombre de la ciudad asociada al evento.
	 *
	 * @return El nombre de la ciudad asociada al evento.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Establece el nombre de la ciudad asociada al evento.
	 *
	 * @param city El nuevo nombre de la ciudad asociada al evento.
	 */
	public void setCity(String city) {
		this.city = city;
	}
}
