package com.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Transient
    private Product product;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Transient
    private User user;

    @Column
    private int count = 1;

    @Column(name = "order_id")
    private Long orderId;

    @Transient
    private ShopOrder order;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Cart() {}
    public Cart(long productId, long userId, Long orderId, int count, Date createdAt, Date updatedAt) {
        this.productId = productId;
        this.userId = userId;
        this.orderId = orderId;
        this.count = count;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrder(ShopOrder order) {
        this.order = order;
    }
    public ShopOrder getOrder() {
        return order;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreatedAt() {
        return createdAt;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdatedAt() {
        return updatedAt;
    }
}
