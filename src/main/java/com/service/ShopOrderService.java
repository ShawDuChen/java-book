package com.service;

import com.model.ShopOrder;

import java.util.List;

public interface ShopOrderService {
    public void add(ShopOrder shopOrder);
    public void update(ShopOrder shopOrder);
    public void delete(long id);
    public ShopOrder findById(long id);
    public List<ShopOrder> getAll();
    public List<ShopOrder> search(int page, int size);
    public void pay(long id);
    public ShopOrder createOrder(List<Long> ids, long userId);
}
