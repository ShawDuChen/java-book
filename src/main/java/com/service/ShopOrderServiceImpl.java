package com.service;

import com.model.ShopOrder;
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
        return query.getResultList();
    }

    @Override
    public List<ShopOrder> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<ShopOrder> query = session.createQuery("from shop_order order by createdAt", ShopOrder.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
