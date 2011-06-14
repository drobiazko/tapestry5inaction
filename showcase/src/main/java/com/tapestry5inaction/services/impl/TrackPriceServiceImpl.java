package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Track;
import com.tapestry5inaction.services.TrackPriceService;

public class TrackPriceServiceImpl implements TrackPriceService {

	public Double getPrice(Track track) {
		return 0.99;
	}

}
