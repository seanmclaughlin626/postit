package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
}
