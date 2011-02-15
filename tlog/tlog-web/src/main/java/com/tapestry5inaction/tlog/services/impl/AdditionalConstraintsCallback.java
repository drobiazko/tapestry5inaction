package com.tapestry5inaction.tlog.services.impl;

import org.hibernate.Criteria;

public interface AdditionalConstraintsCallback {

    void apply(Criteria criteria);
}
