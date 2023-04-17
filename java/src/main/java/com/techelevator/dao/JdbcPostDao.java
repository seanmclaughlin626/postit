package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT post_id, p.author_id, forum_id, title, upvotes, downvotes, content, time_created, time_formatted, last_interaction, u.username FROM posts AS p JOIN users AS u ON p.author_id = u.user_id";
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
        String sql = "SELECT post_id, p.author_id, forum_id, title, upvotes, downvotes, content, time_created, time_formatted, last_interaction, u.username FROM posts AS p JOIN users AS u ON p.author_id = u.user_id WHERE post_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            post = mapRowToPost(result);
        }
        return post;
    }

    @Override
    public List<Post> getPostsByForumId(int id) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT post_id, p.author_id, title, upvotes, downvotes, content, time_created, time_formatted, u.username, forum_id FROM posts AS p JOIN users AS u ON p.author_id = u.user_id WHERE forum_id = ? ORDER BY time_created DESC";
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
        String sql = "INSERT INTO posts (author_id, title, content, forum_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,post.getAuthor(), post.getTitle(), post.getContent(), post.getForumId());

        String updateSql = "UPDATE forums SET last_interaction = now() WHERE forum_id = ?";
        jdbcTemplate.update(updateSql, post.getForumId());
    }

    @Override
    public void deletePost(Post post){
        String deleteCommentsSql = "DELETE FROM comments WHERE post_id = ?";
        jdbcTemplate.update(deleteCommentsSql, post.getPostId());
        String sql = "DELETE FROM posts WHERE post_id = ?";
        jdbcTemplate.update(sql, post.getPostId());
    }

    private Post mapRowToPost(SqlRowSet results){
        Post post = new Post();
        post.setTitle(results.getString("title"));
        post.setPostId(results.getInt("post_id"));
        post.setContent(results.getString("content"));
        post.setAuthor(results.getInt("author_id"));
        int upvoteScore = results.getInt("upvotes") - results.getInt("downvotes");
        post.setUpvoteScore(upvoteScore);
        Timestamp timestamp = results.getTimestamp("time_created");
        post.setTimeFormatted(results.getString("time_formatted"));
        post.setTimeCreated(timestamp.toLocalDateTime());
        Timestamp lastInteractionTimestamp = results.getTimestamp("time_created");
        post.setLastInteraction(lastInteractionTimestamp.toLocalDateTime());
        post.setAuthorName(results.getString("username"));
        post.setForumId(results.getInt("forum_id"));
        return post;
    }


}

