package com.tapestry5book.tlog.core.annotations;


import java.lang.annotation.*;

@Target(
        {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PublicPage {
}
