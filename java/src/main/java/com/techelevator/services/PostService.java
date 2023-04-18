package com.techelevator.services;

import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Post;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PostService {
    private PostDao postDao;
    private UserDao userDao;

    public PostService(PostDao postDao, UserDao userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public void deletePostService(int forumId, String authorName, int postId, String username){
        int authorId = userDao.findIdByUsername(authorName);
        int userId = userDao.findIdByUsername(username);
        List<String> modUsernames = userDao.getModUsernamesByForumId(forumId);
        List<Integer> adminIds = userDao.getAdminUserIds();
        if(userId == authorId || modUsernames.contains(username) || adminIds.contains(userId)){
            postDao.deletePost(postId);
        }
    }
}
