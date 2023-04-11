package com.techelevator.controller;

import com.techelevator.dao.CommentDao;
import com.techelevator.model.Comment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommentContoller {
    private CommentDao commentDao;

    public CommentContoller(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable int id){
        return commentDao.getCommentById(id);
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable int id){
        return commentDao.getCommentsByPostId(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comments")
    public boolean createComment(@RequestBody Comment comment){
        return commentDao.createComment(comment);
    }
}
