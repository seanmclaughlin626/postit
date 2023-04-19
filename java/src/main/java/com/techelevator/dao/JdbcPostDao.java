package com.techelevator.dao;

import com.techelevator.model.Post;
import com.techelevator.model.User;
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
        String sql = "SELECT post_id, p.author_id, f.forum_id, title, upvotes, downvotes, content, time_created, time_formatted, p.last_interaction, u.username, image_url, f.forum_name FROM posts AS p JOIN users AS u ON p.author_id = u.user_id JOIN forums AS f ON p.forum_id = f.forum_id";
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
        String sql = "SELECT post_id, p.author_id, f.forum_id, title, upvotes, downvotes, content, time_created, time_formatted, p.last_interaction, u.username, image_url, f.forum_name FROM posts AS p JOIN users AS u ON p.author_id = u.user_id JOIN forums AS f ON f.forum_id = p.forum_id WHERE post_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            post = mapRowToPost(result);
        }
        return post;
    }

    @Override
    public List<Post> getPostsByForumId(int id) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT post_id, p.author_id, title, upvotes, downvotes, content, time_created, time_formatted, u.username, f.forum_id, image_url, f.forum_name, p.last_interaction FROM posts AS p JOIN users AS u ON p.author_id = u.user_id JOIN forums AS f ON p.forum_id = f.forum_id WHERE f.forum_id = ? ORDER BY time_created DESC";
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
        String sql = "INSERT INTO posts (author_id, title, content, forum_id, image_url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,post.getAuthor(), post.getTitle(), post.getContent(), post.getForumId(), post.getUrl());
        String updateSql = "UPDATE forums SET last_interaction = now() WHERE forum_id = ?";
        jdbcTemplate.update(updateSql, post.getForumId());
    }

    @Override
    public void deletePost(int postId){
        String deleteCommentsSql = "DELETE FROM comments WHERE post_id = ?";
        jdbcTemplate.update(deleteCommentsSql, postId);
        String sql = "DELETE FROM posts WHERE post_id = ?";
        jdbcTemplate.update(sql, postId);
    }

    @Override
    public void addVoted(int userId, int postId, int vote){
        String addVotesSql;
        if (vote == 1){
            addVotesSql = "UPDATE post SET upvotes = upvotes + 1 WHERE post_id = ?;";
        }else{
            addVotesSql = "UPDATE post SET downvotes = downvotes + 1 WHERE post_id = ?;";
        }
    jdbcTemplate.update(addVotesSql, postId);
        String addVotedUserSql = "INSERT INTO voted_post(user_id, post_id) VALUES (?, ?);";
        jdbcTemplate.update(addVotedUserSql, userId, postId);
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
        post.setUrl(results.getString("image_url"));
        post.setForumName(results.getString("forum_name"));
        return post;
    }


}

