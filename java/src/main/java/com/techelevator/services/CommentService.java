package com.techelevator.services;

import com.techelevator.dao.CommentDao;
import com.techelevator.dao.ForumDao;
import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {
    private UserDao userDao;
    private CommentDao commentDao;
    private PostDao postDao;

    public CommentService(UserDao userDao, CommentDao commentDao, PostDao postDao) {
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.postDao = postDao;
    }

    public void deleteCommentService(int commentId, int authorId, int postId, String username){
        int userId = userDao.findIdByUsername(username);
        Post post = postDao.getPostById(postId);
        List<String> modUsernames = userDao.getModUsernamesByForumId(post.getForumId());
        List<Integer> adminIds = userDao.getAdminUserIds();
        if(userId == authorId || modUsernames.contains(username) || adminIds.contains(userId)){
            commentDao.deleteComment(commentId);
        }
    }
}