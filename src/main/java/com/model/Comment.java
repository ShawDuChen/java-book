package com.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false, length = 255)
    private String content;

    @Column(name = "product_id", updatable = false, nullable = false)
    private long productId;

    @Transient
    private Product product;

    @Column(name = "user_id", updatable = false, nullable = false)
    private long userId;

    @Transient
    private User user;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Comment() {}

    public Comment(String content, long productId, long userId, Date createdAt, Date updatedAt) {
        this.content = content;
        this.productId = productId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreatedAt() {
        return createdAt;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
