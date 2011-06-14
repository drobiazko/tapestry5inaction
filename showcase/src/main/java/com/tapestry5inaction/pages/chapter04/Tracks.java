package com.tapestry5inaction.pages.chapter04;

import com.tapestry5inaction.entities.ShoppingCart;
import com.tapestry5inaction.entities.Track;
import com.tapestry5inaction.services.MusicLibrary;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class Tracks {
    @Inject
    private MusicLibrary musicLibrary;

    @Property
    private List<Track> tracks;

    @Property
    private Track currentTrack;

    @SessionState
    private ShoppingCart shoppingCart;

    void setupRender() {
        tracks = musicLibrary.getTracks();
    }

    Object onAddToCart(Track track) {
        shoppingCart.addTrack(track);

        return ViewCart.class;
    }
}