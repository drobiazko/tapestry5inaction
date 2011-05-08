package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.Option;
import com.tapestry5inaction.entities.Vote;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

public interface VoteService {

    Vote findById(Long id);

    Option findOptionById(Long id);

    void persist(Vote vote);
}
