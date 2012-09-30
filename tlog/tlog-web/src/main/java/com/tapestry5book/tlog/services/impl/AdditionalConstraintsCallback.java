package com.tapestry5book.tlog.services.impl;

import org.hibernate.Criteria;

public interface AdditionalConstraintsCallback {

    void apply(Criteria criteria);
}
