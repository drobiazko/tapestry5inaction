package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Option;
import com.tapestry5inaction.services.VoteService;
import org.apache.tapestry5.ValueEncoder;

public class OptionEncoder implements ValueEncoder<Option> {

    private VoteService voteService;

    public OptionEncoder(VoteService voteService) {
        this.voteService = voteService;
    }

    public String toClient(Option option) {
        return String.valueOf(option.getId());
    }

    public Option toValue(String clientValue) {
        Long id = Long.valueOf(clientValue);

        return voteService.findOptionById(id);
    }
}
