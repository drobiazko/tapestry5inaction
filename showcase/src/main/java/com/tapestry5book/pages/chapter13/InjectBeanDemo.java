package com.tapestry5book.pages.chapter13;

import com.tapestry5book.services.impl.EchoBean;
import org.apache.tapestry5.ioc.annotations.Inject;

public class InjectBeanDemo {

    @Inject
    private EchoBean echoBean;

    public String getMessage() {
        return echoBean.echo("Hello, Spring bean!");
    }
}