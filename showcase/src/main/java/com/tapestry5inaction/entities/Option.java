package com.tapestry5inaction.entities;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
