package com.tapestry5book.pages.chapter12;

import com.tapestry5book.entities.Article;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

public class Read {

    @PageActivationContext
    @Property
    private Article article;

}