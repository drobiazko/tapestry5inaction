package com.tapestry5book.services.impl;

import com.tapestry5book.entities.Vote;
import com.tapestry5book.services.VoteService;
import org.apache.tapestry5.ValueEncoder;

public class VoteEncoder implements ValueEncoder<Vote> {
    private VoteService voteService;

    public VoteEncoder(VoteService voteService) {
        this.voteService = voteService;
    }

    public String toClient(Vote vote) {
        return String.valueOf(vote.getId());
    }

    public Vote toValue(String clientValue) {
        Long id = Long.valueOf(clientValue);
        return voteService.findById(id);
    }
}
