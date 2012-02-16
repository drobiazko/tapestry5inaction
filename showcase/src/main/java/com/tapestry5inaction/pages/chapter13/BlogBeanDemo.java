package com.tapestry5inaction.pages.chapter13;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.impl.BlogBean;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class BlogBeanDemo {

    @Inject
    @Property
    private BlogBean blogBean;

    @Property
    private Article currentArticle;
}