package com.tapestry5inaction.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<Option>();

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public void setOptions(final List<Option> options) {
        this.options = options;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
