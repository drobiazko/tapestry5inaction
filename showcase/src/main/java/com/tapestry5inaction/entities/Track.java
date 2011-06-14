package com.tapestry5inaction.entities;

import org.apache.tapestry5.beaneditor.NonVisual;

/**
 * One track from a music library.
 */
public class Track {
	private Long id;

	private String album, artist, genre, title;

	private int playCount, rating;

	@NonVisual
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtist() {
		return artist;
	}

	public String getGenre() {
		return genre;
	}

	public int getPlayCount() {
		return playCount;
	}

	/**
	 * Rating as a value between 0 and 100.
	 */
	public int getRating() {
		return rating;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
