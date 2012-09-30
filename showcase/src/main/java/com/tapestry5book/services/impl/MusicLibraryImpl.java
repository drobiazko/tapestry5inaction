package com.tapestry5book.services.impl;

import com.tapestry5book.entities.Track;
import com.tapestry5book.services.MusicLibrary;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.slf4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class MusicLibraryImpl implements MusicLibrary {

    private final List<Track> tracks;

    private final Map<Long, Track> idToTrack;

    public MusicLibraryImpl(Logger logger) {
        URL library = getClass().getResource("iTunes.xml");

        tracks = new MusicLibraryParser(logger).parseTracks(library);

        idToTrack = CollectionFactory.newMap();

        for (Track t : tracks) {
            idToTrack.put(t.getId(), t);
        }
    }

    public Track getById(long id) {
        Track result = idToTrack.get(id);

        if (result != null)
            return result;

        throw new IllegalArgumentException(String.format(
                "No track with id #%d.", id));
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<Track> findByMatchingTitle(String title) {
        String titleLower = title.toLowerCase();

        List<Track> result = CollectionFactory.newList();

        for (Track t : tracks) {
            if (t.getTitle().toLowerCase().contains(titleLower))
                result.add(t);
        }

        return result;
    }

}
