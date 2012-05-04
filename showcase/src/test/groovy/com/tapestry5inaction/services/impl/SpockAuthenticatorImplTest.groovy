package com.tapestry5inaction.services.impl

import spock.lang.Specification
import com.tapestry5inaction.services.UserDao
import org.apache.tapestry5.services.ApplicationStateManager
import com.tapestry5inaction.entities.User

class SpockAuthenticatorImplTest extends Specification {

    UserDao dao = Mock()
    ApplicationStateManager applicationStateManager = Mock()
    AuthenticatorImpl authenticator;

    def setup() {
        authenticator = new AuthenticatorImpl()
        authenticator.userDao = dao
        authenticator.applicationStateManager = applicationStateManager
    }

    def "authenticate user successfully"() {
        when:
        def user = authenticator.authenticate("admin", "admin")

        then:
        dao.findByName("admin") >> new User("admin", "21232f297a57a5a743894a0e4a801fc3")

        expect:
        user.name == "admin";
        user.password == "21232f297a57a5a743894a0e4a801fc3"

    }

    def "user was not found by name"() {
        when:
        def user = authenticator.authenticate("admin", "admin")

        then:
        dao.findByName("admin") >> null

        expect:
        user == null

    }

    def "wrong password"() {
        when:
        def user = authenticator.authenticate("admin", "admin")

        then:
        dao.findByName("admin") >> new User("admin", "secret")

        expect:
        user == null

    }


    def "user is logged in"() {
        when:
        def loggedIn = authenticator.isLoggedIn()

        then:
        applicationStateManager.exists(User.class) >> userExists

        expect:
        loggedIn == userExists

        where:
        userExists << [true, false]

    }
}