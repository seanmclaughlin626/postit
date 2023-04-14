package com.techelevator.services;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import org.springframework.stereotype.Component;

@Component
public class ForumService {
    private UserDao userDao;
    private ForumDao forumDao;

    public ForumService(UserDao userDao, ForumDao forumDao) {
        this.userDao = userDao;
        this.forumDao = forumDao;
    }

    public int createForumService(Forum forum, String username){
        int id = userDao.findIdByUsername(username);
        return forumDao.createForum(forum, id);
    }

    public void promoteUserToMod(String username, int forumId){
        int userId = userDao.findIdByUsername(username);
        forumDao.promoteUserToMod(userId, forumId);
    }
}
