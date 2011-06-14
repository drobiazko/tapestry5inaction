package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Track;
import com.tapestry5inaction.services.MusicLibrary;
import org.apache.tapestry5.ValueEncoder;

public class TrackEncoder implements ValueEncoder<Track> {
    private MusicLibrary library;

    public TrackEncoder(MusicLibrary library) {
        this.library = library;
    }

    public String toClient(Track value) {
        return String.valueOf(value.getId());
    }

    public Track toValue(String clientValue) {
        Long id = Long.valueOf(clientValue);

        return library.getById(id);
    }
}
