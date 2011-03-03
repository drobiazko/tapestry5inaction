package com.tapestry5inaction.pages.chapter03.blog.v5;


import com.tapestry5inaction.entities.Article;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;

public class Read {

    @ActivationRequestParameter("articleId")
    @Property(write = false)
    private Article article;


}
