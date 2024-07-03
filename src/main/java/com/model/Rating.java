package com.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double score;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Transient
    private Product product;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Transient
    private User user;

    @Column(name = "created_at", updatable = false, insertable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", updatable = true, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Rating() {}
    public Rating(double score, long productId, long userId, Date createdAt, Date updatedAt) {
        this.score = score;
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
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
