package com.tapestry5book.services.impl;

import com.tapestry5book.entities.User;
import com.tapestry5book.services.UserDao;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.test.TapestryTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGAuthenticatorImplTest extends TapestryTestCase {

    private UserDao userDao;
    private ApplicationStateManager applicationStateManager;
    private AuthenticatorImpl authenticator;

    @BeforeMethod
    protected void setUp() {
        userDao = newMock(UserDao.class);
        applicationStateManager = mockApplicationStateManager();

        authenticator = new AuthenticatorImpl(userDao, applicationStateManager);
    }

    @Test
    public void authenticateSuccessfully() {
        expect(userDao.findByName("admin")).andReturn(new User("admin", "21232f297a57a5a743894a0e4a801fc3"));
        replay();

        User user = authenticator.authenticate("admin", "admin");

        verify();

        assertNotNull(user);
        assertEquals(user.getName(), "admin");
        assertEquals(user.getPassword(), "21232f297a57a5a743894a0e4a801fc3");
    }

    @Test
    public void authenticateUserNotFound() {
        expect(userDao.findByName("admin")).andReturn(null);

        replay();

        User user = authenticator.authenticate("admin", "wrong");

        verify();

        assertNull(user);
    }

    @Test
    public void authenticateWrongPassword() {
        expect(userDao.findByName("admin")).andReturn(new User("admin", "secret"));

        replay();

        User user = authenticator.authenticate("admin", "wrong");

        verify();

        assertNull(user);
    }

    @Test
    public void userIsLoggedIn() {

        expect(applicationStateManager.exists(User.class)).andReturn(true);

        replay();

        boolean loggedIn = authenticator.isLoggedIn();

        verify();

        assertTrue(loggedIn);
    }

    @Test
    public void userIsNotLoggedIn() {

        expect(applicationStateManager.exists(User.class)).andReturn(false);

        replay();

        boolean loggedIn = authenticator.isLoggedIn();

        verify();

        assertFalse(loggedIn);
    }
}
