package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    List<Post> getPosts();

    Post getPostById(int id);

    void createPost(Post post);

    List<Post> getPostsByForumId(int id);
}
