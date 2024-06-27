package com.service;

import com.model.Category;

import java.util.List;

public interface CategoryService {
    public void add(Category category);
    public void update(Category category);
    public void delete(long id);
    public Category findById(long id);
    public List<Category> getAll();
    public List<Category> search(int page, int size);
}
