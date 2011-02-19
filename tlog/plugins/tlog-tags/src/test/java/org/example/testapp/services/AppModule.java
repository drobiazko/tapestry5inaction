package org.example.testapp.services;


import com.tapestry5inaction.tlog.core.services.CoreModule;
import org.apache.tapestry5.hibernate.HibernateEntityPackageManager;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;

@SubModule(CoreModule.class)
public class AppModule {
}
