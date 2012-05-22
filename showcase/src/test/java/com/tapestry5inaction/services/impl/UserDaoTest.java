package com.tapestry5inaction.services.impl;


import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.AppModule;
import com.tapestry5inaction.services.UserDao;
import org.apache.tapestry5.hibernate.HibernateCoreModule;
import org.apache.tapestry5.hibernate.HibernateModule;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.TapestryModule;
import org.apache.tapestry5.test.TapestryTestCase;
import org.hibernate.Session;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserDaoTest extends TapestryTestCase {

    private Registry registry;

    @BeforeClass
    protected void setUp() {

        registry = new RegistryBuilder()
                .add(TapestryModule.class, HibernateCoreModule.class, HibernateModule.class, AppModule.class, TestModule.class).build();

        registry.performRegistryStartup();

        Session session = registry.getService(Session.class);

        session.save(new User("root", "21232f297a57a5a743894a0e4a801fc3"));

        session.flush();

    }


    @AfterClass
    public void shutdown() {
        registry.shutdown();
    }

    public static class TestModule {

        @Contribute(SymbolProvider.class)
        @ApplicationDefaults
        public static void provideApplicationDefaults(MappedConfiguration<String, String> configuration) {
            configuration.add("tapestry.app-package", "com.tapestry5inaction");
        }
    }

    @Test
    public void findByNonExistingName() {
        UserDao dao = registry.getService(UserDao.class);

        User user = dao.findByName("igor");

        assertNull(user);
    }

    @Test
    public void findByName() {

        UserDao dao = registry.getService(UserDao.class);

        User user = dao.findByName("root");

        assertNotNull(user);
    }

}
