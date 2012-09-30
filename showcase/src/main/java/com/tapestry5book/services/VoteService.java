package com.tapestry5book.services;

import com.tapestry5book.entities.Option;
import com.tapestry5book.entities.Vote;

public interface VoteService {

    Vote findById(Long id);

    Option findOptionById(Long id);

    void persist(Vote vote);
}
