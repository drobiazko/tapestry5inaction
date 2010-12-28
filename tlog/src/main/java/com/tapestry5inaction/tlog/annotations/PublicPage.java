package com.tapestry5inaction.tlog.annotations;


import java.lang.annotation.*;

@Target(
        {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PublicPage {
}
