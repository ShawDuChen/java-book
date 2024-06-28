package com.service;

import com.model.User;
import com.utils.BCryptHandle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
        Session session = getSession();
        String password = user.getPassword();
        String encodePassword = BCryptHandle.encrypt(password);
        user.setPassword(encodePassword);
        user.setCreatedAt(new Date());
        session.save(user);
    }

    @Override
    public User login(User user) {
        Session session = getSession();
        User result = (User) session.createQuery("FROM user WHERE username = :username")
                .setParameter("username", user.getUsername())
                .uniqueResult();
        if (result == null) {
            return null;
        }
        Boolean checkIn = BCryptHandle.verify(user.getPassword(), result.getPassword());
        if (!checkIn) {
            return null;
        }
        return result;
    }

    @Override
    public void add(User user) {
        register(user);
    }

    @Override
    public void update(User user) {
        Session session = getSession();
        user.setUpdatedAt(new Date());
        String password = BCryptHandle.encrypt(user.getPassword());
        user.setPassword(password);
        session.update(user);
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public List<User> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<User> query = session.createQuery("from user order by createdAt desc", User.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<User> getAll(String username) {
        Session session = getSession();
        Query<User> query = session.createQuery("from user where (username is null or username like :username) order by createdAt desc", User.class);
        query.setParameter("username", "%" +(username != null ? username : "") + "%");
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        Session session = getSession();
        return session.get(User.class, id);
    }
}
