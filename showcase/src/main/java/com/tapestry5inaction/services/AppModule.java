package com.tapestry5inaction.services;

import com.tapestry5inaction.services.impl.BlogServiceImpl;
import com.tapestry5inaction.services.impl.DemoDataParser;
import com.tapestry5inaction.services.impl.DemoDataSource;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Autobuild;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Startup;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;

import java.io.IOException;

public class AppModule {


    public static void bind(ServiceBinder binder){
        binder.bind(BlogService.class, BlogServiceImpl.class);
    }

    public static DemoDataParser buildDemoDataParser(Logger logger) {
        return new DemoDataParser(logger);
    }


    @Startup
    public static void initDemoData(@Autobuild
                                    final DemoDataSource source) {
        source.create();
    }

    /**
     * This is a service definition, the service will be named "TimingFilter".
     * The interface, RequestFilter, is used within the RequestHandler service
     * pipeline, which is built from the RequestHandler service configuration.
     * Tapestry IoC is responsible for passing in an appropriate Logger
     * instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * <p/>
     * <p/>
     * Service builder methods are useful when the implementation is inline as
     * an inner class (as here) or require some other kind of special
     * initialization. In most cases, use the static bind() method instead.
     * <p/>
     * <p/>
     * If this method was named "build", then the service id would be taken from
     * the service interface and would be "RequestFilter". Since Tapestry
     * already defines a service named "RequestFilter" we use an explicit
     * service id that we can reference inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log) {
        return new RequestFilter() {
            public boolean service(Request request, Response response,
                                   RequestHandler handler) throws IOException {
                long startTime = System.currentTimeMillis();

                try {
                    // The responsibility of a filter is to invoke the
                    // corresponding method
                    // in the handler. When you chain multiple filters together,
                    // each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This
     * is how we extend Tapestry using the timing filter. A common use for this
     * kind of filter is transaction management or security. The
     *
     * @Local annotation selects the desired service by type, but only from the
     * same module. Without
     * @Local, there would be an error due to the other service(s) that
     * implement RequestFilter (defined in other modules).
     */
    public void contributeRequestHandler(
            OrderedConfiguration<RequestFilter> configuration,
            @Local RequestFilter filter) {
        // Each contribution to an ordered configuration has a name, When
        // necessary, you may
        // set constraints to precisely control the invocation order of the
        // contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }
}
