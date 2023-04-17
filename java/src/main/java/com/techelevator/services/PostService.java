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

    public void deletePostService(Post post, String username){
        int userId = userDao.findIdByUsername(username);
        List<String> modUsernames = userDao.getModUsernamesByForumId(post.getForumId());
        List<Integer> adminIds = userDao.getAdminUserIds();
        if(userId == post.getAuthor() || modUsernames.contains(username) || adminIds.contains(userId)){
            postDao.deletePost(post);
        }
    }
}
