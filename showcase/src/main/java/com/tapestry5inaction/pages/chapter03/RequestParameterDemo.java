package com.tapestry5inaction.pages.chapter03;


import com.tapestry5inaction.entities.Article;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;

public class RequestParameterDemo {

    @Property(write = false)
    private Article article;

    void onActivate(@RequestParameter("articleId") Article article) {
        this.article = article;
    }

}
