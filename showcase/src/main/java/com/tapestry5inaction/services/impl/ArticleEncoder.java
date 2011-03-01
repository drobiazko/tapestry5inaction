package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.ValueEncoder;

public class ArticleEncoder implements ValueEncoder<Article> {

    private BlogService blogService;

    public ArticleEncoder(BlogService blogService) {
        this.blogService = blogService;
    }

    public String toClient(Article article) {
        return String.valueOf(article.getId());
    }

    public Article toValue(String clientValue) {
        Long id = Long.valueOf(clientValue);
        return blogService.findArticleById(id);
    }
}
