package com.tapestry5inaction.services;

public interface PageableLoopDataSource {

    int getAvailableRows();

    void prepare(int startIndex, int endIndex);

    List<?> getRow();
}