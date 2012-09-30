package com.tapestry5book.pages.chapter04;

import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import java.util.Random;

public class DiscardDemo {

    @Property
    @Persist
    private Integer randomNumber;

    void onNextRandom() {
        randomNumber = new Random().nextInt();
    }

    @DiscardAfter
    void onClear() {
    }
}