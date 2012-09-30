package com.tapestry5book.tlog.core.services;


import com.tapestry5book.tlog.core.entities.Month;
import com.tapestry5book.tlog.core.entities.Tag;
import org.apache.tapestry5.Link;

public interface StartPageLinkSource {

    Link getLink(Month month);

    Link getLink(Tag link);
}
