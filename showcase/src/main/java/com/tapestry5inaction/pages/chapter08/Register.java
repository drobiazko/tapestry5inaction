package com.tapestry5inaction.pages.chapter08;

import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.UserDao;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Register {

    @Property
    private User user;

    @Property
    @Validate("required")
    private String passwordConfirmed;

    @InjectComponent
    private BeanEditForm registerForm;

    @Inject
    private Messages messages;

    @Inject
    private UserDao userDao;

    void onValidateFromRegisterForm() {
        if (registerForm.isValid()) {
            if (!user.getPassword().equals(passwordConfirmed)) {
                registerForm.recordError(messages.get("password-confirmation-failed"));

                return;
            }

            final User existingUser = userDao.findByName(user.getName());

            if(existingUser!=null){
                registerForm.recordError(messages.format("user-already-exists", user.getName()));
            }
        }
    }

    @CommitAfter
    void onSuccess(){
        userDao.persist(user);
    }
}