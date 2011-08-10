package com.tapestry5inaction.pages.chapter09.blog;

import com.tapestry5inaction.entities.Article;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

public class Read {
    @PageActivationContext
    @Property(write = false)
    private Article article;
}
