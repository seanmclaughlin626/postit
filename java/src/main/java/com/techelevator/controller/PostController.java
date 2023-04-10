package com.techelevator.controller;

import com.techelevator.dao.JdbcPostDao;
import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private PostDao postDao;

    PostController(JdbcTemplate jdbcTemplate){
        this.postDao = new JdbcPostDao(jdbcTemplate);
    }

    @RequestMapping (path = "/posts", method = RequestMethod.GET)
    public List<Post> getPosts (){
        return postDao.getPosts();
    }

    @RequestMapping (path = "/posts/{id}", method = RequestMethod.GET)
    public Post getPostBYId(@PathVariable int id){
        return postDao.getPostById(id);
    }

    @RequestMapping (path = "/forums/{id}/posts", method = RequestMethod.GET)
    public List<Post> getPostsBYForumId(@PathVariable int id){
        return postDao.getPostsByForumId(id);
    }

    @RequestMapping (path = "/posts", method = RequestMethod.POST)
    public void createPost(@RequestBody Post post){
        postDao.createPost(post);
    }
}
