package com.service;

import com.model.Product;
import com.model.Rating;
import com.model.User;
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
public class RatingServiceImpl implements RatingService {
    @Autowired
    private SessionFactory sessionFactory;;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Rating add(Rating rating) {
        Session session = getSession();
        User user = session.get(User.class, rating.getUserId());
        rating.setUser(user);
        rating.setUserId(user.getId());
        Product product = session.get(Product.class, rating.getProductId());
        rating.setProduct(product);
        rating.setProductId(product.getId());
        rating.setCreatedAt(new Date());
        session.persist(rating);
        return rating;
    }

    @Override
    public Rating update(Rating rating) {
        Session session = getSession();
        User user = session.get(User.class, rating.getUserId());
        rating.setUser(user);
        rating.setUserId(user.getId());
        Product product = session.get(Product.class, rating.getProductId());
        rating.setProduct(product);
        rating.setProductId(product.getId());
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
        Query<Rating> query = session.createQuery("from rating where (user is not null) AND (product is not null) order by createdAt desc", Rating.class);
        List<Rating> result = query.getResultList();
        formatResult(result);
        return result;
    }

    @Override
    public List<Rating> findByProductId(String productId) {
        Session session = getSession();
        Product product = productId == null ? null : session.get(Product.class, Long.parseLong(productId));
        Query<Rating> query = session.createQuery("from rating c where (:product is null or c.product=coalesce(:product,c.product)) order by createdAt desc", Rating.class);
        query.setParameter("product", product);
        List<Rating> result = query.getResultList();
        formatResult(result);
        return result;
    }

    @Override
    public List<Rating> findByUserId(String userId) {
        Session session = getSession();
        User user = userId == null ? null : session.get(User.class, Long.parseLong(userId));
        Query<Rating> query = session.createQuery("from rating c where (:user is null or c.user=coalesce(:user,c.user)) order by createdAt desc", Rating.class);
        query.setParameter("user", user);
        List<Rating> result = query.getResultList();
        formatResult(result);
        return result;
    }

    private void formatResult(List<Rating> ratings) {
        for (Rating rating : ratings) {
            User user = rating.getUser();
            rating.setUserId(user.getId());
            Product product = rating.getProduct();
            rating.setProductId(product.getId());
        }
    }
}

