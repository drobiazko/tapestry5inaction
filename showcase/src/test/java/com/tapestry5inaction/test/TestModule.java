package com.tapestry5inaction.test;

import com.tapestry5inaction.services.PageableLoopDataSource;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.ioc.services.TypeCoercer;

import java.util.Collection;

public class TestModule {

    @Contribute(TypeCoercer.class)
    public static void provideCoercionTuples(Configuration<CoercionTuple> configuration) {

        configuration.add(CoercionTuple.create(Collection.class, PageableLoopDataSource.class,
                new Coercion<Collection, PageableLoopDataSource>() {
                    public PageableLoopDataSource coerce(Collection input) {
                        return new CollectionPageableLoopDataSource(input);
                    }
                }));
    }
}
