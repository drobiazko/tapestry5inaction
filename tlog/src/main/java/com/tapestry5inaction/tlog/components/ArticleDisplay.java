package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.entities.Article;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class ArticleDisplay {

    @Parameter(required = true)
    @Property
    private Article article;
}
