package com.service;

import com.model.ShopOrder;
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
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(ShopOrder shopOrder) {
        Session session = getSession();
        shopOrder.setCreatedAt(new Date());
        session.save(shopOrder);
    }

    @Override
    public void update(ShopOrder shopOrder) {
        Session session = getSession();
        shopOrder.setUpdatedAt(new Date());
        session.update(shopOrder);
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        ShopOrder shopOrder = session.get(ShopOrder.class, id);
        session.delete(shopOrder);
    }

    @Override
    public ShopOrder findById(long id) {
        Session session = getSession();
        return session.get(ShopOrder.class, id);
    }

    @Override
    public List<ShopOrder> getAll() {
        Session session = getSession();
        Query<ShopOrder> query = session.createQuery("from shop_order ORDER BY createdAt desc", ShopOrder.class);
        List<ShopOrder> result = query.getResultList();
        for (ShopOrder shopOrder : result) {
            User user = shopOrder.getUser();
            if (user != null) {
                shopOrder.setUserId(user.getId());
            }
        }
        return result;
    }

    @Override
    public List<ShopOrder> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<ShopOrder> query = session.createQuery("from shop_order order by createdAt", ShopOrder.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        List<ShopOrder> result = query.getResultList();
        for (ShopOrder shopOrder : result) {
            User user = shopOrder.getUser();
            if (user != null) {
                shopOrder.setUserId(user.getId());
            }
        }
        return result;
    }

    @Override
    public void pay(long id) {
        Session session = getSession();
        ShopOrder shopOrder = session.get(ShopOrder.class, id);
        shopOrder.setPaid(1);
        shopOrder.setUpdatedAt(new Date());
        session.update(shopOrder);
    }

    @Override
    public ShopOrder createOrder(List<Long> ids, long userId) {
        Session session = getSession();
        User user = session.get(User.class, userId);
        ShopOrder order = new ShopOrder();
        order.setUser(user);
        order.setPaid(0);
        Date now = new Date();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        session.persist(order);
        Query query = session.createQuery("update cart c set c.order=:order where c.id in :ids AND c.order is null");
        query.setParameter("order", order);
        query.setParameterList("ids", ids);
        query.executeUpdate();
        return order;
    }
}
