package com.tapestry5inaction.pages;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import java.util.Arrays;
import java.util.List;

public class PageableLoopDemo {

    @PageActivationContext
    @Property
    private int currentPage;

    @Property
    private String letter;

    public List<String> getAlphabet() {
        return Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "V", "X", "Y", "Z");
    }
}
