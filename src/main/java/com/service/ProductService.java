package com.service;

import com.model.Product;

import java.util.List;

public interface ProductService {
    public void add(Product product);
    public void update(Product product);
    public void delete(long id);
    public Product findById(long id);
    public List<Product> getAll(String name);
    public List<Product> search(int page, int size);
    public List<Product> getHots();
}
