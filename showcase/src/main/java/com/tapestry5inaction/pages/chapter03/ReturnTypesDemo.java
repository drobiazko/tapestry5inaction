package com.tapestry5inaction.pages.chapter03;


import com.tapestry5inaction.pages.Index;
import org.apache.tapestry5.annotations.InjectPage;

public class ReturnTypesDemo {

    @InjectPage
    private Index index;

    Object onNavigateByPageClass() {
        return Index.class;
    }

    Object onNavigateByPageName() {
        return "Index";
    }

    Object onNavigateByPageInstance() {
        return index;
    }
}
