package com.tapestry5book.pages.chapter10;

import com.tapestry5book.components.SimpleLoop;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Mixins;

public class MixinsAnnotationDemo {

    @Mixins("RenderInformals")
    @Component(parameters = {"source=1..5", "value=var:value"})
    private SimpleLoop<Integer> loop;
}