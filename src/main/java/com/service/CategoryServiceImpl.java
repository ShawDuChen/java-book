package com.service;

import com.model.Category;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Category category) {
        Session session = getSession();
        category.setCreatedAt(new Date());
        session.save(category);
    }

    @Override
    public void update(Category category) {
        Session session = getSession();
        category.setUpdatedAt(new Date());
        session.update(category);
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        Category category = session.get(Category.class, id);
        session.delete(category);
    }

    @Override
    public Category findById(long id) {
        Session session = getSession();
        return session.get(Category.class, id);
    }

    @Override
    public List<Category> getAll(String name) {
        Session session = getSession();
        Query<Category> query = session.createQuery("from category where (name is null or name like :name) order by createdAt", Category.class);
        query.setParameter("name", "%"+ (name != null ? name : "") + "%");
        return query.getResultList();
    }

    @Override
    public List<Category> search(int page, int size) {
        int start = (page - 1) * size;
        Session session = getSession();
        Query<Category> query = session.createQuery("from category order by createdAt", Category.class);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
