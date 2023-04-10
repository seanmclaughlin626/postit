package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.model.Forum;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ForumController {

    private ForumDao forumDao;

    public ForumController(ForumDao forumDao) {
        this.forumDao = forumDao;
    }

    @GetMapping("/forums")
    public List<Forum> getForumsController(){
        return forumDao.getForums();
    }

    @GetMapping("/forums/{id}")
    public Forum getForumByIdController(@PathVariable int id){
        return forumDao.getForumById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/forums")
    public boolean createForumController(@RequestBody Forum forum){
        return forumDao.createForum(forum);
    }
}
