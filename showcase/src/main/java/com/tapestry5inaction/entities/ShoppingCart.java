package com.tapestry5inaction.entities;

import java.util.ArrayList;
import java.util.List;

import com.tapestry5inaction.services.TrackPriceService;

public class ShoppingCart {

    private TrackPriceService trackPriceService;
    private List<Track> tracks = new ArrayList<Track>();

    public ShoppingCart(TrackPriceService trackPriceService) {
        super();
        this.trackPriceService = trackPriceService;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Double getSum() {
        double sum = 0;
        for (Track next : tracks) {
            sum += getPrice(next);
        }
        return sum;
    }

    public Double getPrice(Track track) {
        return trackPriceService.getPrice(track);
    }
}
