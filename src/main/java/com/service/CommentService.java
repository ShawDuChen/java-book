package com.service;

import com.model.Comment;

import java.util.List;

public interface CommentService {
    public Comment add(Comment comment);
    public Comment update(Comment comment);
    public void delete(long commentId);
    public List<Comment> all(String productId, String userId);
    public List<Comment> findProductComments(String productId);
    public List<Comment> findUserComments(String userId);
}
