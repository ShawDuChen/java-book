package com.service;

import com.model.Comment;
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
public class CommentServiceImpl implements CommentService {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Comment add(Comment comment) {
        Session session = getSession();
        comment.setCreatedAt(new Date());
        session.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        Session session = getSession();
        comment.setUpdatedAt(new Date());
        session.update(comment);
        return comment;
    }

    @Override
    public void delete(long commentId) {
        Session session = getSession();
        Comment comment = (Comment) session.get(Comment.class, commentId);
        session.delete(comment);
    }

    @Override
    public List<Comment> all(String productId, String userId) {
        Session session = getSession();
        Query query = session.createQuery("select c, p, u from comment c " +
                        "left join product p on (p.id = c.productId) " +
                        "left join user u on (u.id = c.userId) " +
                        "where (coalesce(:productId, c.productId) = c.productId or c.productId is null) and " +
                        "(coalesce(:userId, c.userId) = c.userId or c.userId is null) " +
                        "order by c.createdAt desc"
        );
        query.setParameter("productId", productId == null ? null : Long.parseLong(productId));
        query.setParameter("userId", userId == null ? null : Long.parseLong(userId));
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    @Override
    public List<Comment> findProductComments(String productId) {
        Session session = getSession();
        Query query = session.createQuery("select c, p, u from comment c " +
                "left join product p on (p.id = c.productId) " +
                "left join user u on (u.id = c.userId) " +
                "where (coalesce(:productId,c.productId)=c.productId or c.productId is null) order by c.createdAt desc"
        );
        query.setParameter("productId", productId == null ? null : Long.parseLong(productId));
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    @Override
    public List<Comment> findUserComments(String userId) {
        Session session = getSession();
        Query query = session.createQuery("select c, p, u from comment c " +
                "left join product p on (p.id = c.productId) " +
                "left join user u on (u.id = c.userId) " +
                "where (coalesce(:userId,c.userId)=c.userId or c.userId is null) order by c.createdAt desc"
        );
        query.setParameter("userId", userId == null ? null : Long.parseLong(userId));
        List<Object[]> result = query.getResultList();
        return serialize(result);
    }

    private List<Comment> serialize(List<Object[]> result) {
        List<Comment> list = new ArrayList<>();
        for (Object[] row : result) {
            Comment comment = (Comment) row[0];
            Product product = (Product) row[1];
            comment.setProduct(product);
            User user = (User) row[2];
            comment.setUser(user);
            list.add(comment);
        }
        return list;
    }
}
