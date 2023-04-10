package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
        String sql = "SELECT forum_id, forum_name FROM forums";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            results.add(mapRowToForum(rowSet));
        }
        return results;
    }

    @Override
    public Forum getForumById(int id){
        Forum results = new Forum();
        String sql = "SELECT forum_id, forum_name FROM forums WHERE forum_id = ?";
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
        return forum;
    }
}
