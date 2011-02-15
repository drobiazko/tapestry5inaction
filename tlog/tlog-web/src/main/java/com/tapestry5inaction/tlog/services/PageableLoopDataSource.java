package com.tapestry5inaction.tlog.services;

import com.tapestry5inaction.tlog.entities.Article;

import java.util.List;

public interface PageableLoopDataSource {

    int getAvailableRows();

    void prepare(int startIndex, int endIndex);

    List<?> getRow();
}
