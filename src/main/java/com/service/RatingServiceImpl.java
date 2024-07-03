package com.service;

import com.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {
    @Autowired
    private SessionFactory sessionFactory;;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Rating add(Rating rating) {
        Session session = getSession();
        rating.setCreatedAt(new Date());
        session.persist(rating);
        return rating;
    }

    @Override
    public Rating update(Rating rating) {
        Session session = getSession();
        rating.setUpdatedAt(new Date());
        session.update(rating);
        return rating;
    }

    @Override
    public long delete(long id) {
        Session session = getSession();
        Rating rating = session.get(Rating.class, id);
        session.delete(rating);
        return id;
    }

    @Override
    public List<Rating> all() {
        Session session = getSession();
        Query query = session.createQuery("select r, p, u from rating r "
                + "left join product p on p.id = r.productId "
                + "left join user u on u.id = r.userId "
        );
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    @Override
    public List<Rating> findByProductId(String productId) {
        Session session = getSession();
        Query query = session.createQuery("select r, p, u from rating r "
                + "left join product p on p.id = r.productId "
                + "left join user u on u.id = r.userId "
                + "where ((:productId,r.productId)=r.productId or r.productId is null) order by r.createdAt desc"
        );
        query.setParameter("productId", productId == null ? null : Long.parseLong(productId));
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    @Override
    public List<Rating> findByUserId(String userId) {
        Session session = getSession();
        Query query = session.createQuery("select r, p, u from rating r "
                + "left join product p on p.id = r.productId "
                + "left join user u on u.id = r.userId "
                + "where ((:userId,r.userId)=r.userId or r.userId is null) order by r.createdAt desc"
        );
        query.setParameter("userId", userId == null ? null : Long.parseLong(userId));
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    private List<Rating> serialize(List<Object[]> result) {
        List<Rating> list = new ArrayList<>();
        for (Object[] row : result) {
            Rating rating = (Rating) row[0];
            Product product = (Product) row[1];
            rating.setProduct(product);
            User user = (User) row[2];
            rating.setUser(user);
            list.add(rating);
        }
        return list;
    }
}

