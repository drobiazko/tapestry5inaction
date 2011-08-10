package com.tapestry5inaction.components;

import com.tapestry5inaction.services.PageableLoopDataSource;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

@SupportsInformalParameters
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

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    @Property
    private String element;

    @Property(write = false)
    private int previousPage;

    @Property(write = false)
    private int nextPage;

    @Inject
    private ComponentResources componentResources;


    void setupRender() {
        int availableRows = source.getAvailableRows();

        if (availableRows == 0)
            return;

        int maxPage = ((availableRows - 1) / rowsPerPage) + 1;

        int current = currentPage == 0 ? 1 : currentPage;

        int startIndex = (current - 1) * rowsPerPage;

        int endIndex = Math.min(startIndex + rowsPerPage - 1, availableRows - 1);

        source.prepare(startIndex, endIndex);

        List<?> row = source.getRow();

        if (1 < current && current <= maxPage) {
            previousPage = current - 1;
        }

        if (0 < current && current < maxPage) {
            nextPage = current + 1;
        }

    }

    void onGoToPage(int nextPage) {
        currentPage = nextPage;
    }

    String defaultElement() {
        return componentResources.getElementName();
    }
}