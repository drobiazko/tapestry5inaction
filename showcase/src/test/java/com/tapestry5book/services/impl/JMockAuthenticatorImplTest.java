package com.tapestry5book.services.impl;

import com.tapestry5book.entities.User;
import com.tapestry5book.services.UserDao;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNull;

@RunWith(JMock.class)
public class JMockAuthenticatorImplTest {

    private UserDao userDao;

    private ApplicationStateManager applicationStateManager;

    private AuthenticatorImpl authenticator;

    Mockery mockery = new Mockery();

    @Before
    public void setUp() {

        userDao = mockery.mock(UserDao.class);
        applicationStateManager = mockery.mock(ApplicationStateManager.class);
        authenticator = new AuthenticatorImpl(userDao, applicationStateManager);
    }

    @Test
    public void authenticateSuccessfully() {

        mockery.checking(new Expectations() {{
            oneOf(userDao).findByName("admin");

            will(returnValue(new User("admin", "21232f297a57a5a743894a0e4a801fc3")));
        }});

        User user = authenticator.authenticate("admin", "admin");

        assertNotNull(user);
        assertEquals(user.getName(), "admin");
        assertEquals(user.getPassword(), "21232f297a57a5a743894a0e4a801fc3");
    }

    @Test
    public void authenticateUserNotFound() {

        mockery.checking(new Expectations() {{
            oneOf(userDao).findByName("admin");

            will(returnValue(null));
        }});

        User user = authenticator.authenticate("admin", "admin");

        assertNull(user);
    }

    @Test
    public void authenticateWrongPassword() {

        mockery.checking(new Expectations() {{
            oneOf(userDao).findByName("admin");

            will(returnValue(new User("admin", "secret")));
        }});

        User user = authenticator.authenticate("admin", "admin");

        assertNull(user);
    }

    @Test
    public void userIsLoggedIn() {

        mockery.checking(new Expectations() {{
            oneOf(applicationStateManager).exists(User.class);

            will(returnValue(true));
        }});

        boolean loggedIn = authenticator.isLoggedIn();

        assertTrue(loggedIn);
    }

    @Test
    public void userIsNotLoggedIn() {

        mockery.checking(new Expectations() {{
            oneOf(applicationStateManager).exists(User.class);
            will(returnValue(false));
        }});

        boolean loggedIn = authenticator.isLoggedIn();

        assertFalse(loggedIn);
    }
}
