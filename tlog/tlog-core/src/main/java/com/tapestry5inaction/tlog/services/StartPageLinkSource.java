package com.tapestry5inaction.tlog.services;


import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.entities.Month;
import com.tapestry5inaction.tlog.entities.Tag;
import org.apache.tapestry5.Link;

public interface StartPageLinkSource {

    Link getLink(Month month);

    Link getLink(Tag link);
}
