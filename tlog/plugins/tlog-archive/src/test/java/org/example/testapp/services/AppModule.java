package org.example.testapp.services;


import org.apache.tapestry5.hibernate.HibernateEntityPackageManager;
import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Symbol;

public class AppModule {
    @Contribute(HibernateEntityPackageManager.class)
    public static void provideHibernateEntityPackages(Configuration<String> configuration) {
        configuration.add("com.tapestry5inaction.tlog.entities");
    }
}
