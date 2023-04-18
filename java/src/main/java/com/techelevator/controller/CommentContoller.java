package com.techelevator.controller;

import com.techelevator.dao.CommentDao;
import com.techelevator.model.Comment;
import com.techelevator.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class CommentContoller {
    private CommentDao commentDao;
    private CommentService commentService;

    public CommentContoller(CommentDao commentDao, CommentService commentService) {
        this.commentDao = commentDao;
        this.commentService = commentService;
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable int id) {
        return commentDao.getCommentById(id);
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable int id) {
        return commentDao.getCommentsByPostId(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comments")
    public boolean createComment(@RequestBody Comment comment) {
        return commentDao.createComment(comment);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/comments", method = RequestMethod.DELETE)
    public void deleteComment(@RequestParam("commentId") int commentId, @RequestParam("authorId") int authorId, @RequestParam("postId") int postId, Principal principal) {
        commentService.deleteCommentService(commentId, authorId, postId, principal.getName());
    }
}
