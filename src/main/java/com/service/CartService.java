package com.service;

import com.model.Cart;

import java.util.List;

public interface CartService {
    public void add(Cart cart);
    public void update(Cart cart);
    public void delete(long id);
    public Cart findById(long id);
    public List<Cart> getAll();
    public List<Cart> search(int page, int size);
    public void createOrder(List<Long> ids);
}
