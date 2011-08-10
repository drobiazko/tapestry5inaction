package com.tapestry5inaction.services;

import java.util.List;

public interface PageableLoopDataSource {

    int getAvailableRows();

    void prepare(int startIndex, int endIndex);

    List<?> getRow();
}