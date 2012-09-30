package com.tapestry5book.services;

import java.util.List;

import com.tapestry5book.entities.Track;

public interface MusicLibrary {
	/**
	 * Gets a track by its unique id.
	 * 
	 * @param id
	 *            of track to retrieve
	 * @return the Track
	 * @throws IllegalArgumentException
	 *             if no such track exists
	 */
	Track getById(long id);

	/**
	 * Provides a list of all tracks in an indeterminate order.
	 */
	List<Track> getTracks();

	/**
	 * Performs a case-insensitive search, finding all tracks whose title
	 * contains the input string (ignoring case).
	 * 
	 * @param title
	 *            a partial title
	 * @return a list of all matches
	 */
	List<Track> findByMatchingTitle(String title);
}