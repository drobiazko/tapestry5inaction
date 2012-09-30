package com.tapestry5book.tlog.pages.admin;


import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

public class Logout {

    @Inject
    private Request request;

    Object onActivate() {
        Session session = request.getSession(true);

        session.invalidate();

        return com.tapestry5book.tlog.pages.Index.class;
    }
}
