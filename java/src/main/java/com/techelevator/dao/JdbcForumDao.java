package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.services.ForumService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
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
        String sql = "SELECT forum_id, forum_name, last_interaction FROM forums WHERE forum_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if(rowSet.next()){
            results = mapRowToForum(rowSet);
        }
        return results;
    }

    @Override
    public int createForum(Forum forum, int id){
        int newId = 0;
        String insertUserSql = "INSERT INTO forums (forum_name) values (?) RETURNING forum_id";
        newId = jdbcTemplate.queryForObject(insertUserSql, int.class, forum.getName());

        String makeSelfModSql = "INSERT INTO forum_moderators (forum_id, mod_id) VALUES (?, ?)";
        jdbcTemplate.update(makeSelfModSql, newId, id);

        return newId;
    }

    @Override
    public List<Integer> getModIdsByForumId(int forumId){
        List<Integer> modList = new ArrayList<>();
        String sql = "SELECT mod_id FROM forum_moderators WHERE forum_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, forumId);
        while(result.next()){
            modList.add(result.getInt("mod_id"));
        }
        return modList;
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
