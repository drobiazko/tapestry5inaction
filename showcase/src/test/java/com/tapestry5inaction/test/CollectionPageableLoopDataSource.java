package com.tapestry5inaction.test;

import com.tapestry5inaction.services.PageableLoopDataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionPageableLoopDataSource<T> implements PageableLoopDataSource {

    private List<T> rows;

    private List<T> preparedResult;

    public CollectionPageableLoopDataSource(Collection<T> collection) {
        rows = new ArrayList<T>(collection);
    }

    public int getAvailableRows() {
        return rows.size();
    }

    public void prepare(int startIndex, int endIndex) {
        preparedResult = new ArrayList<T>();

        for (int i = startIndex; i <= endIndex; i++) {
            preparedResult.add(rows.get(i));
        }
    }

    public List<?> getRow() {
        return preparedResult;
    }
}
