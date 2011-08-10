package com.tapestry5inaction.components;

public class PageableLoop {

    @Parameter(required = true)
    @Property
    private PageableLoopDataSource source;

    @Parameter("10")
    private int rowsPerPage;

    @Parameter
    private int currentPage;

    @Parameter(principal = true)
    @Property
    private Object value;

    @Property(write = false)
    private int previousPage;

    @Property(write = false)
    private int nextPage;


    void setupRender() {
        int availableRows = source.getAvailableRows();

        if (availableRows == 0)
            return;

        int maxPage = ((availableRows - 1) / rowsPerPage) + 1;

        if(currentPage == 0) {
            currentPage = 1;
        }

        //int current = currentPage == 0 ? 1 : currentPage;

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

    void onGoToPage(int nextPage) {
        currentPage = nextPage;
    }
}