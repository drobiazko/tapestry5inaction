package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Vote;
import com.tapestry5inaction.services.VoteService;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;

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
