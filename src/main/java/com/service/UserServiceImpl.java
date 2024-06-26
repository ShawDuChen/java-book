package com.service;

import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void register(User user) {
        getSession().save(user);
    }

    @Override
    public User login(User user) {
        return (User) getSession().createQuery("FROM User WHERE username = :username AND password = :password")
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .uniqueResult();
    }
}
