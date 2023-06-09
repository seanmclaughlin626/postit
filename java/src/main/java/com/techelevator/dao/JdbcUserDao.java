package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role) {
        String insertUserSql = "insert into users (username,password_hash,role) values (?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole) == 1;
    }
    @Override
    public List<String> getUsersBySearchQuery(String search) {
        List<String> userList = new ArrayList<>();
        String sql = "SELECT username FROM users WHERE LOWER(username) ILIKE '%'||?||'%'";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, search.toLowerCase());
        while (rowSet.next()) {
            userList.add(rowSet.getString("username"));
        }
        return userList;
    }

    @Override
    public List<String> getNonModsBySearchQuery(String search, int forumId) {
        List<String> userList = new ArrayList<>();
        String sql = "SELECT username\n" +
                "FROM users\n" +
                "WHERE LOWER(username) ILIKE '%'||?||'%' \n" +
                "AND user_id NOT IN \n" +
                "(SELECT user_id \n" +
                " FROM users \n" +
                " JOIN forum_moderators ON users.user_id = forum_moderators.mod_id\n" +
                " WHERE forum_id = ?)";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, search.toLowerCase(), forumId);
        while (rowSet.next()) {
            userList.add(rowSet.getString("username"));
        }
        return userList;
    }

    @Override
    public List<String> getModUsernamesByForumId(int forumId){
        List<String> userList = new ArrayList<>();
        String sql = " SELECT username\n" +
                " FROM users\n" +
                " JOIN forum_moderators ON users.user_id = forum_moderators.mod_id\n" +
                " WHERE forum_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, forumId);
        while (rowSet.next()) {
            userList.add(rowSet.getString("username"));
        }
        return userList;
    }

    @Override
    public List<Integer> getAdminUserIds(){
        List<Integer> adminUsersIds = new ArrayList<>();
        String sql = "SELECT user_id from users WHERE role = 'ROLE_ADMIN'";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            adminUsersIds.add(rowSet.getInt("user_id"));
        }
        return adminUsersIds;
    }


    @Override
    public List<Integer>getAllVotedPostUserByPostId(int postId){
        List<Integer> votedUser = new ArrayList<>();
        String sql = "SELECT user_id FROM voted_post WHERE post_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);
        while(results.next()){
            votedUser.add(results.getInt("user_id"));
        }
        return votedUser;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
}
