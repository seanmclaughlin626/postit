package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Search;
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
    public int getUserIdByUsername(Principal principal){
       return userDao.findIdByUsername(principal.getName());
   }

    @GetMapping("/search-users")
    public List<String> getUsersBySearchQueryController(@RequestBody Search search){
        return userDao.getUsersBySearchQuery(search.getSearchInput());
    }
}
