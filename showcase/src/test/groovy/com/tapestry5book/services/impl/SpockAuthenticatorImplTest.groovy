package com.tapestry5book.services.impl

import spock.lang.Specification
import com.tapestry5book.services.UserDao
import org.apache.tapestry5.services.ApplicationStateManager
import com.tapestry5book.entities.User

class SpockAuthenticatorImplTest extends Specification {

    UserDao dao = Mock()
    ApplicationStateManager applicationStateManager = Mock()
    AuthenticatorImpl authenticator;

    def setup() {
        authenticator = new AuthenticatorImpl(dao, applicationStateManager)
    }

    def "authenticate user successfully"() {
        when:
        def user = authenticator.authenticate("admin", "admin")

        then:
        dao.findByName("admin") >>
                new User("admin", "21232f297a57a5a743894a0e4a801fc3")

        then:
        user.name == "admin";
        user.password == "21232f297a57a5a743894a0e4a801fc3"

    }

    def "user was not found by name"() {
        when:
        def user = authenticator.authenticate("admin", "admin")

        then:
        dao.findByName("admin") >> null

        then:
        user == null

    }

    def "wrong password"() {
        when:
        def user = authenticator.authenticate("admin", "admin")

        then:
        dao.findByName("admin") >> new User("admin", "secret")

        then:
        user == null

    }


    def "user is logged in"() {
        when:
        def loggedIn = authenticator.isLoggedIn()

        then:
        applicationStateManager.exists(User.class) >> userExists

        then:
        loggedIn == userExists

        where:
        userExists << [true, false]

    }
}