package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.UserDao;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Inject
    private Session session;

    public User findById(Long id) {
        return (User) session.get(User.class, id);
    }

    public User findByName(String name) {
        return (User) session.createCriteria(User.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<User> findAll() {
        return session.createCriteria(User.class).list();
    }

    public void persist(User user) {
        session.saveOrUpdate(user);
    }
}
