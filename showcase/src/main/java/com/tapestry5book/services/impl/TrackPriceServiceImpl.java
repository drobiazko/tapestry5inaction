package com.tapestry5book.services.impl;

import com.tapestry5book.entities.Track;
import com.tapestry5book.services.TrackPriceService;

public class TrackPriceServiceImpl implements TrackPriceService {

	public Double getPrice(Track track) {
		return 0.99;
	}

}
