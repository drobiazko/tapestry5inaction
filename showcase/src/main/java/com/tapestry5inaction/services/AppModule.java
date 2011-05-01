package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.entities.Blog;
import com.tapestry5inaction.services.impl.*;
import org.apache.tapestry5.*;
import org.apache.tapestry5.hibernate.HibernateSymbols;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.*;
import org.apache.tapestry5.services.messages.ComponentMessagesSource;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Currency;

public class AppModule {


    public static void bind(ServiceBinder binder) {
        binder.bind(Authenticator.class, AuthenticatorImpl.class);
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

    @Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public static void provideApplicationDefaults(MappedConfiguration<String, String> configuration) {

        configuration.add(HibernateSymbols.PROVIDE_ENTITY_VALUE_ENCODERS, "false");

        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,de,ru,iw");
    }

    @Contribute(ApplicationStateManager.class)
    public static void provideApplicationStateContributions(
            final MappedConfiguration<Class, ApplicationStateContribution> configuration,
            final BlogService blogService) {

        final ApplicationStateCreator<Blog> creator = new ApplicationStateCreator<Blog>() {
            public Blog create() {
                return blogService.findBlog();
            }

        };
        configuration.add(Blog.class, new ApplicationStateContribution(
                PersistenceConstants.SESSION, creator));
    }

    @Contribute(ValueEncoderSource.class)
    public static void provideEncoders(
            MappedConfiguration<Class, ValueEncoderFactory> configuration,
            final BlogService blogService) {

        ValueEncoderFactory<Article> factory = new ValueEncoderFactory<Article>() {

            public ValueEncoder<Article> create(Class<Article> clazz) {
                return new ArticleEncoder(blogService);
            }
        };

        configuration.add(Article.class, factory);

    }

    @Contribute(ComponentMessagesSource.class)
    public static void provideMessages(
            @Value("/com/tapestry5inaction/Messages.properties")
            Resource resource,
            OrderedConfiguration<Resource> configuration) {
        configuration.add("Messages", resource);
    }

    @Contribute(TranslatorSource.class)
    public static void provideTranslators(
            MappedConfiguration<Class, Translator> configuration) {

        configuration.add(Currency.class, new CurrencyTranslator());
    }

    @Contribute(NullFieldStrategySource.class)
    public static void provideNullFieldStrategies(
            MappedConfiguration<String, NullFieldStrategy> configuration) {

        configuration.add("currency", new CurrencyNullFieldStrategy());
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
