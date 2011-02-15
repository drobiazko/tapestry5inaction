package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.services.PageableLoopDataSource;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import java.util.List;

public class PageableLoop {

    @Parameter(required = true)
    @Property
    private PageableLoopDataSource source;

    @Parameter("10")
    private int rowsPerPage;

    @Parameter
    private Integer currentPage;

    @Parameter(principal = true)
    @Property
    private Object value;

    @Property(write = false)
    private Integer previousPage;

    @Property(write = false)
    private Integer nextPage;


    void setupRender() {
        int availableRows = source.getAvailableRows();

        if (availableRows == 0)
            return;

        int maxPage = ((availableRows - 1) / rowsPerPage) + 1;

        int currentPage = getCurrentPage();

        int startIndex = (currentPage - 1) * rowsPerPage;

        int endIndex = Math.min(startIndex + rowsPerPage - 1, availableRows - 1);

        source.prepare(startIndex, endIndex);

        List<?> row = source.getRow();


        if (1 < currentPage && currentPage <= maxPage) {
            previousPage = currentPage - 1;
        }

        if (0 < currentPage && currentPage < maxPage) {
            nextPage = currentPage + 1;
        }

    }

    public int getCurrentPage() {
        return currentPage == null ? 1 : currentPage;
    }
}
