package com.tapestry5book.pages.chapter09.blog;

import com.tapestry5book.entities.Article;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

public class Read {
    @PageActivationContext
    @Property(write = false)
    private Article article;
}
