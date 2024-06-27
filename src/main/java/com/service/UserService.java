package com.service;

import com.model.User;

import java.util.List;

public interface UserService {
    void register(User user);
    User login(User user);
    void add(User user);
    void update(User user);
    void delete(long id);
    User findById(long id);
    List<User> search(int page, int size);
    List<User> getAll();
}
