package com.tapestry5inaction.tlog.tags.services.impl;


import com.tapestry5inaction.tlog.core.entities.Tag;
import com.tapestry5inaction.tlog.tags.services.TagService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;

public class TagServiceImpl implements TagService {

    @Inject
    private Session session;

    public List<Tag> findTags() {
        return session.createCriteria(Tag.class).list();
    }
}
