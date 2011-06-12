package com.tapestry5inaction.pages.chapter04;

import org.apache.tapestry5.annotations.PageReset;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import java.util.Random;

public class RandomNumberDemo {
    @Property
    @Persist
    private Integer randomNumber;

    void onNextRandom() {
        randomNumber = new Random().nextInt();
    }

    @PageReset
    void reset() {
        randomNumber = 0;
    }

}