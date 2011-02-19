package com.tapestry5inaction.tlog.core.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany
    private List<Article> articles = new ArrayList<Article>();

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Blog addArticle(final Article article) {
        this.articles.add(article);

        return this;
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(final List<Article> articles) {
        this.articles = articles;
    }

}
