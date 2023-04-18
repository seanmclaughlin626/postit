package com.techelevator.dao;

import com.techelevator.model.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> getCommentsByPostId(int id);

    Comment getCommentById(int id);

    boolean createComment(Comment comment);

    void deleteComment(int commentId);
}
