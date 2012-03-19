package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.UserDao;
import org.apache.tapestry5.ioc.test.TestBase;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MockitoAuthenticatorImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private ApplicationStateManager applicationStateManager;

    private AuthenticatorImpl authenticator;

    @Before
    public void setUp() {

        authenticator = TestBase.create(AuthenticatorImpl.class, "userDao", userDao, "applicationStateManager", applicationStateManager);
    }

    @Test
    public void authenticateSuccessfully() {

        when(userDao.findByName("admin")).thenReturn(new User("admin", "21232f297a57a5a743894a0e4a801fc3"));

        User user = authenticator.authenticate("admin", "admin");

        assertNotNull(user);
        assertEquals(user.getName(), "admin");
        assertEquals(user.getPassword(), "21232f297a57a5a743894a0e4a801fc3");
    }

    @Test
    public void authenticateUserNotFound() {

        User user = authenticator.authenticate("admin", "wrong");

        assertNull(user);
    }

    @Test
    public void authenticateWrongPassword() {

        when(userDao.findByName("admin")).thenReturn(new User("admin", "secret"));

        User user = authenticator.authenticate("admin", "admin");

        assertNull(user);
    }


    @Test
    public void userIsLoggedIn() {

        when(applicationStateManager.exists(User.class)).thenReturn(true);

        boolean loggedIn = authenticator.isLoggedIn();

        assertTrue(loggedIn);
    }

    @Test
    public void userIsNotLoggedIn() {

        when(applicationStateManager.exists(User.class)).thenReturn(false);

        boolean loggedIn = authenticator.isLoggedIn();

        assertFalse(loggedIn);
    }

}
