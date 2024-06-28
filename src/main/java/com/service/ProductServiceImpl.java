package com.service;

import com.model.Category;
import com.model.Product;
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
        return session.get(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        Session session = getSession();
        Query<Product> query = session.createQuery("from product ORDER BY createdAt desc", Product.class);
        List<Product> result = query.getResultList();
        for (Product product : result) {
            Category category = product.getCategory();
            if (category != null) {
                product.setCategoryId(category.getId());
            }
        }
        return result;
    }

    @Override
    public List<Product> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<Product> query = session.createQuery("from product order by createdAt", Product.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        List<Product> result = query.getResultList();
        for (Product product : result) {
            Category category = product.getCategory();
            if (category != null) {
                product.setCategoryId(category.getId());
            }
        }
        return result;
    }
}
