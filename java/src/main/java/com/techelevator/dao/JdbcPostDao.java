package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostDao implements PostDao{

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


@Override
public List<Post> getPosts() {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT post_id, author_id, title, upvotes, downvotes, content FROM posts";
    SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
    while(result.next()){
        Post post = new Post();
        post = mapRowToPost(result);
        posts.add(post);
    }
    return posts;
}


    @Override
    public Post getPostById(int id) {
        Post post = new Post();
        String sql = "SELECT post_id, author_id, title, upvotes, downvotes, content FROM posts WHERE post_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            post = mapRowToPost(result);
        }
        return post;
    }

    @Override
    public List<Post> getPostsByForumId(int id) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT post_id, author_id, title, upvotes, downvotes, content FROM posts WHERE forum_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()){
            Post post = new Post();
            post = mapRowToPost(result);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO posts (author_id, title, content) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,post.getAuthor(), post.getTitle(), post.getContent());
    }

    private Post mapRowToPost(SqlRowSet results){
        Post post = new Post();
        post.setTitle(results.getString("title"));
        post.setPostId(results.getInt("post_id"));
        post.setContent(results.getString("content"));
        post.setAuthor(results.getInt("author_id"));
        int upvoteScore = results.getInt("upvotes") + results.getInt("downvotes");
        post.setUpvoteScore(upvoteScore);
        return post;
    }


}
