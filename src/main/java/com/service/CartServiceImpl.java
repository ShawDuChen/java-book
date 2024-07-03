package com.service;

import com.model.Cart;
import com.model.Category;
import com.model.Product;
import com.model.User;
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
        session.save(cart);
    }

    @Override
    public void update(Cart cart) {
        Session session = getSession();
        cart.setUpdatedAt(new Date());
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
        return null;
    }

    @Override
    public List<Cart> getAll(String userId) {
        Session session = getSession();
        Query query = session.createQuery(queryString("where (coalesce(:userId, c.userId)=c.userId or c.userId is null) AND c.orderId is null "));
        query.setParameter("userId", userId == null ? null : Long.parseLong(userId));
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    @Override
    public List<Cart> search(int page, int size) {
        Session session = getSession();
        Query query = session.createQuery(queryString("where c.orderId is null "));
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    private List<Cart> serialize(List<Object[]> result) {
        List<Cart> carts = new ArrayList<>();
        for (Object[] row : result) {
            Cart cart = (Cart) row[0];
            Product product = (Product) row[1];
            cart.setProduct(product);
            User user = (User) row[2];
            cart.setUser(user);
            Category category = (Category) row[3];
            product.setCategory(category);
            carts.add(cart);
        }
        return carts;
    }

    private String queryString(String where) {
        return "select c, p, u, ca from cart c "
                + "left join product p on (p.id = c.productId) "
                + "left join user u on (u.id = c.userId) "
                + "left join category ca on (ca.id = p.categoryId) "
                + (where == null ? "" : where)
                + "order by c.createdAt desc";
    }
}
