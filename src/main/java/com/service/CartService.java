package com.service;

import com.model.Cart;
import com.model.ShopOrder;

import java.util.List;

public interface CartService {
    public void add(Cart cart);
    public void update(Cart cart);
    public void delete(long id);
    public Cart findById(long id);
    public List<Cart> getAll(String userId);
    public List<Cart> search(int page, int size);
}
