package com.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private long productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Transient
    private long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private int count = 1;

    @Transient
    private long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private ShopOrder order;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Cart() {}
    public Cart(long productId, Product product, long userId, User user, int count, long orderId, ShopOrder order, Date createdAt, Date updatedAt) {
        this.productId = productId;
        this.product = product;
        this.userId = userId;
        this.user = user;
        this.count = count;
        this.orderId = orderId;
        this.order = order;
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
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public long getOrderId() {
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
