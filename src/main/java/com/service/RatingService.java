package com.service;

import com.model.Rating;

import java.util.List;

public interface RatingService {
    public Rating add(Rating rating);
    public Rating update(Rating rating);
    public long delete(long id);
    public List<Rating> all();
    public List<Rating> findByProductId(String productId);
    public List<Rating> findByUserId(String userId);
}