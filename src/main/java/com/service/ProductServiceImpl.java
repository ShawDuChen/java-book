package com.service;

import com.model.Cart;
import com.model.Category;
import com.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Query query = session.createQuery(
                queryString(null, "where p.id=:id ")
        );
        query.setParameter("id", id);
        List<Product> result = serializeResult(query.getResultList());
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public List<Product> getAll(String name, String categoryId) {
        Session session = getSession();
        Category category = categoryId == null ? null : session.get(Category.class, Long.parseLong(categoryId));
        Query query = session.createQuery(
                queryString(null, "where (p.name is null or p.name like :name) AND (coalesce(:category, p.category)=p.category or p.category is null) ")
        );
        query.setParameter("name", "%" + (name != null ? name : "") + "%");
        query.setParameter("category", category);
        List<Object[]> result = query.getResultList();
        return serializeResult(result);
    }

    @Override
    public List<Product> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query query = session.createQuery(queryString(null, null));
        query.setFirstResult(start);
        query.setMaxResults(size);
        List<Object[]> result = query.getResultList();
        return serializeResult(result);
    }

    @Override
    public List<Product> getHots() {
        Session session = getSession();
        Query query = session.createQuery(queryString("order by sellCount desc", null));
        query.setFirstResult(0);
        query.setMaxResults(20);
        List<Object[]> result = query.getResultList();
        return serializeResult(result);
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

    private List<Product> serializeResult(List<Object[]> result) {
        List<Product> productList = new ArrayList<>();
        for (Object[] row : result) {
            Product product = (Product)row[0];
            double score = row[1] == null ? 0 : (double)row[1];
            long sellCount = row[2] == null ? 0 : (long)row[2];
            product.setScore(score);
            product.setSellCount(sellCount);
            Category category = product.getCategory();
            product.setCategoryId(category.getId());
            productList.add(product);
        }
        return productList;
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

    public String queryString(String orderBy, String where) {
        return "select p, ROUND(avg(r.score), 2) as avgScore, sum(c.count) as sellCount from product p "
                + "LEFT join rating r on p = r.product "
                + "LEFT join cart c on (p = c.product and c.order is not null) "
                + (where == null ? "" : where)
                + "group by p.id " + (orderBy == null ? "order by p.createdAt desc" : orderBy);
    }
}
