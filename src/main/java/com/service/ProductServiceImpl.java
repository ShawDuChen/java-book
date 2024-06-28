package com.service;

import com.model.Cart;
import com.model.Category;
import com.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Product product) {
        Session session = getSession();
        product.setCreatedAt(new Date());
        Category category = session.get(Category.class, product.getCategoryId());
        product.setCategory(category);
        session.save(product);
    }

    @Override
    public void update(Product product) {
        Session session = getSession();
        product.setUpdatedAt(new Date());
        Category category = session.get(Category.class, product.getCategoryId());
        product.setCategory(category);
        session.update(product);
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    @Override
    public Product findById(long id) {
        Session session = getSession();
        Product product = session.get(Product.class, id);
        if (product == null) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        Query<Cart> query = session.createQuery("from cart c where c.product = :product AND c.order IS NOT null", Cart.class);
        query.setParameter("product", product);
        List<Cart> cart = query.getResultList();
        product.setSellCount(cart.size());
        return product;
    }

    @Override
    public List<Product> getAll(String name, String categoryId) {
        Session session = getSession();
        Category category = categoryId == null ? null : session.get(Category.class, Long.parseLong(categoryId));
        Query<Product> query = session.createQuery("from product where (name is null or name like :name) AND (:category is null or category=:category) order by createdAt desc", Product.class);
        query.setParameter("name", "%" + (name != null ? name : "") + "%");
        query.setParameter("category", category);
        List<Product> result = query.getResultList();
        Map<Long, Integer> map = getProductSellCountMap(session);
        formatResult(result, map);
        return result;
    }

    @Override
    public List<Product> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<Product> query = session.createQuery("from product order by createdAt desc", Product.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        List<Product> result = query.getResultList();
        Map<Long, Integer> map = getProductSellCountMap(session);
        formatResult(result, map);
        return result;
    }

    @Override
    public List<Product> getHots() {
        Session session = getSession();
        Query query = session.createQuery("select c.product, count(c) from cart c where c.order is not null group by c.product");
        query.setFirstResult(0);
        query.setMaxResults(20);
        List<Object[]> carts = query.getResultList();
        List<Long> ids = new ArrayList<>();
        for (Object[] row : carts) {
            ids.add(((Product)row[0]).getId());
        }
        Query<Product> pQuery = session.createQuery("from product p where p.id in :ids", Product.class);
        pQuery.setParameterList("ids", ids);
        List<Product> result = pQuery.getResultList();
        Map<Long, Integer> map = getProductSellCountMap(session);
        formatResult(result, map);
        return result;
    }

    public void formatResult(List<Product> result, Map<Long, Integer> map) {
        for (Product product : result) {
            Category category = product.getCategory();
            if (category != null) {
                product.setCategoryId(category.getId());
            }
            if (map.containsKey(product.getId())) {
                int sellCount = map.get(product.getId());
                product.setSellCount(sellCount);
            }
        }
    }

    public Map<Long, Integer> getProductSellCountMap(Session session) {
        Query<Cart> query = session.createQuery("from cart c where c.order is not null", Cart.class);
        List<Cart> cartList = query.getResultList();
        Map<Long, Integer> map = new HashMap<>();
        for (Cart cart : cartList) {
            long productId = cart.getProduct().getId();
            if (!map.containsKey(productId)) {
                map.put(productId, 0);
            }
            map.put(productId, map.get(productId) + 1);
        }
        return map;
    }
}
