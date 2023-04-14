package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.model.Forum;
import com.techelevator.services.ForumService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ForumController {

    private ForumDao forumDao;
    private final ForumService forumService;

    public ForumController(ForumDao forumDao, ForumService forumService) {
        this.forumDao = forumDao;
        this.forumService = forumService;
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
    @ResponseStatus(HttpStatus.CREATED)
    public int createForumController(@RequestBody Forum forum, Principal principal){
        return forumService.createForumService(forum, principal.getName());
    }
}
