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
        Product product = session.get(Product.class, comment.getProductId());
        User user = session.get(User.class, comment.getUserId());
        comment.setCreatedAt(new Date());
        comment.setProduct(product);
        comment.setUser(user);
        session.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        Session session = getSession();
        Product product = session.get(Product.class, comment.getProductId());
        User user = session.get(User.class, comment.getUserId());
        comment.setUpdatedAt(new Date());
        comment.setProduct(product);
        comment.setUser(user);
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
        Product product = productId == null ? null : session.get(Product.class, Long.parseLong(productId));
        User user = userId == null ? null : session.get(User.class, Long.parseLong(userId));
        Query<Comment> query = session.createQuery(
                "from comment c where " +
                        "(coalesce(:product, c.product) = c.product or c.product is null) and " +
                        "(coalesce(:user, c.user) = c.user or c.user is null) " +
                        "order by c.createdAt desc",
                Comment.class
        );
        query.setParameter("product", product);
        query.setParameter("user", user);
        List<Comment> result = query.getResultList();
        updateResult(result);
        return result;
    }

    @Override
    public List<Comment> findProductComments(String productId) {
        Session session = getSession();
        Product product = productId == null ? null : session.get(Product.class, Long.parseLong(productId));
        Query<Comment> query = session.createQuery("from comment where (product is null or product=:product) order by createdAt desc", Comment.class);
        query.setParameter("product", product);
        List<Comment> result = query.getResultList();
        updateResult(result);
        return result;
    }

    @Override
    public List<Comment> findUserComments(String userId) {
        Session session = getSession();
        User user = userId == null ? null : session.get(User.class, Long.parseLong(userId));
        Query<Comment> query = session.createQuery("from comment where (user is null or user=:user) order by createdAt desc", Comment.class);
        query.setParameter("user", user);
        List<Comment> result = query.getResultList();
        updateResult(result);
        return result;
    }

    private void updateResult(List<Comment> result) {
        for (Comment comment : result) {
            Product p = comment.getProduct();
            User u = comment.getUser();
            comment.setProductId(p.getId());
            comment.setUserId(u.getId());
        }
    }
}
