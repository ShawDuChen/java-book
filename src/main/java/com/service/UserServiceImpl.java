package com.service;

import com.model.User;
import com.utils.BCryptHandle;
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
        String password = user.getPassword();
        String encodePassword = BCryptHandle.encrypt(password);
        user.setPassword(encodePassword);
        getSession().save(user);
    }

    @Override
    public User login(User user) {
        User result = (User) getSession().createQuery("FROM user WHERE username = :username")
                .setParameter("username", user.getUsername())
                .uniqueResult();
        Boolean checkIn = BCryptHandle.verify(user.getPassword(), result.getPassword());
        if (!checkIn) {
            return null;
        }
        return result;
    }
}
