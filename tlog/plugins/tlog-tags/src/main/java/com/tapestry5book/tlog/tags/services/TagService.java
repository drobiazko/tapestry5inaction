package com.tapestry5book.tlog.tags.services;


import com.tapestry5book.tlog.core.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTags();
}
