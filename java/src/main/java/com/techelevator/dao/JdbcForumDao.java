package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcForumDao implements ForumDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Forum> getForums(){
        List<Forum> results = new ArrayList<>();
        String sql = "SELECT forum_id, forum_name, last_interaction FROM forums ORDER BY last_interaction DESC";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            results.add(mapRowToForum(rowSet));
        }
        return results;
    }

    @Override
    public Forum getForumById(int id){
        Forum results = new Forum();
        String sql = "SELECT forum_id, forum_name FROM forums, last_interaction WHERE forum_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if(rowSet.next()){
            results = mapRowToForum(rowSet);
        }
        return results;
    }

    @Override
    public boolean createForum(Forum forum){
        String insertUserSql = "INSERT INTO forums (forum_name) values (?)";

        return jdbcTemplate.update(insertUserSql, forum.getName()) == 1;
    }

    private Forum mapRowToForum(SqlRowSet rowSet){
        Forum forum = new Forum();
        forum.setId(rowSet.getInt("forum_id"));
        forum.setName(rowSet.getString("forum_name"));
        Timestamp timestamp = rowSet.getTimestamp("last_interaction");
        forum.setLastInteraction(timestamp.toLocalDateTime());
        return forum;
    }
}
