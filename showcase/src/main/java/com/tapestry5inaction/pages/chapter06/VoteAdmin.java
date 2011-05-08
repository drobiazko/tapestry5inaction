package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.Option;
import com.tapestry5inaction.entities.Vote;
import com.tapestry5inaction.services.VoteService;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class VoteAdmin {
    @Property
    @PageActivationContext(passivate = false)
    private Vote vote;

    @Property
    private Option currentOption;

    @Inject
    private VoteService voteService;

    @Inject
    private Messages messages;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String message;

    void onPrepare() {
        if (vote == null) {
            vote = new Vote();
        }
    }

    public boolean isVotePersisted() {
        return vote != null && vote.getId() != null;
    }

    Object onAddRowFromOptions() {
        Option option = new Option();

        vote.getOptions().add(option);

        voteService.persist(vote);

        return option;
    }

    void onRemoveRowFromOptions(final Option option) {
        vote.getOptions().remove(option);

        voteService.persist(vote);
    }

    void onSuccess() {
        voteService.persist(vote);

        message = messages.format("vote-persisted", vote.getOptions().size());
    }

    Object[] onPassivate() {
        if (isVotePersisted()) {
            return new Object[]{vote};
        }

        return new Object[]{};
    }

}