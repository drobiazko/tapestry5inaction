package com.tapestry5inaction.pages.chapter10;

import com.tapestry5inaction.components.SimpleLoop;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.MixinClasses;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.corelib.mixins.RenderInformals;

public class MixinClassesAnnotationDemo {

    @MixinClasses(RenderInformals.class)
    @Component(parameters = {"source=1..5", "value=var:value"})
    private SimpleLoop<Integer> loop;
}