package com.service;

import com.model.Cart;
import com.model.Product;
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
public class CartServiceImpl implements CartService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Cart cart) {
        Session session = getSession();
        cart.setCreatedAt(new Date());

        Product product = session.get(Product.class, cart.getProductId());
        cart.setProduct(product);
        User user = session.get(User.class, cart.getUserId());
        cart.setUser(user);

        session.save(cart);
    }

    @Override
    public void update(Cart cart) {
        Session session = getSession();
        cart.setUpdatedAt(new Date());

        Product product = session.get(Product.class, cart.getProductId());
        cart.setProduct(product);
        User user = session.get(User.class, cart.getUserId());
        cart.setUser(user);

        session.update(cart);
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        Cart cart = session.get(Cart.class, id);
        session.delete(cart);
    }

    @Override
    public Cart findById(long id) {
        Session session = getSession();
        return session.get(Cart.class, id);
    }

    @Override
    public List<Cart> getAll() {
        Session session = getSession();
        Query<Cart> query = session.createQuery("from cart ORDER BY createdAt desc", Cart.class);
        return query.getResultList();
    }

    @Override
    public List<Cart> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<Cart> query = session.createQuery("from cart order by createdAt", Cart.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
