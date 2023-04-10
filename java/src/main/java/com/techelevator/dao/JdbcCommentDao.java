package com.techelevator.dao;

import com.techelevator.model.Comment;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
    public class JdbcCommentDao implements CommentDao{

        private final JdbcTemplate jdbcTemplate;

        public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public List<Comment> getCommentsByPostId(int id){
            List<Comment> results = new ArrayList<>();
            String sql = "SELECT comment_id, author_id, content, post_id FROM comments WHERE post_id = ?";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
            while (rowSet.next()){
                results.add(mapRowToComment(rowSet));
            }
            return results;
        }

    @Override
    public Comment getCommentById(int id) {
            Comment results = new Comment();
            String sql = "SELECT comment_id, author_id, post_id, content FROM comments WHERE comment_id = ?";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
            if(rowSet.next()){
                results = mapRowToComment(rowSet);
            }
        return results;
    }

    @Override
    public boolean createComment(Comment comment) {
            String insertCommentSql = "INSERT INTO comments(author_id, post_id, content) values (?, ?, ?)";
        return jdbcTemplate.update(insertCommentSql, comment.getAuthorId(), comment.getContent(), comment.getPostId()) == 1;
    }

    private Comment mapRowToComment(SqlRowSet rowSet){
            Comment comment = new Comment();
            comment.setCommentId(rowSet.getInt("comment_id"));
            comment.setAuthorId(rowSet.getInt("author_id"));
            comment.setContent(rowSet.getString("content"));
            comment.setPostId(rowSet.getInt("post_id"));
            return comment;
    }
}
