package com.tapestry5inaction.tlog.blogroll.entities;


import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExternalBlog {

    @Id
    @GeneratedValue
    @NonVisual
    private Long id;

    @Column
    private String name;

    @Column
    private String uri;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
