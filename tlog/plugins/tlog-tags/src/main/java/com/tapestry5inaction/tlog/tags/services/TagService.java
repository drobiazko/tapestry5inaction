package com.tapestry5inaction.tlog.tags.services;


import com.tapestry5inaction.tlog.core.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTags();
}
