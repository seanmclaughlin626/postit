package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Search;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin

public class UserController {
   private UserDao userDao;
   public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

   @PreAuthorize("isAuthenticated()")
   @GetMapping("/creator")
    public int getMyUserIdByUsername(Principal principal){
       return userDao.findIdByUsername(principal.getName());
   }

    @GetMapping("/search-users")
    public List<String> getUsersBySearchQueryController(@RequestParam("searchInput") String search){
        return userDao.getUsersBySearchQuery(search);
    }

    @GetMapping("/{forumId}/non-mod-search")
    public List<String> searchForNonModsControl(@RequestParam("searchInput") String search, @PathVariable int forumId){
       return userDao.getNonModsBySearchQuery(search, forumId);
    }

    @GetMapping("/{forumId}/get-mods")
    public List<String> getModsForForum(@PathVariable int forumId){
       return userDao.getModUsernamesByForumId(forumId);
    }

    @GetMapping("/posts/{postId}/get-users-voted")
    public List<Integer> getUserIdForPost(@PathVariable int postId){
       return userDao.getAllVotedPostUserByPostId(postId);
    }
}
