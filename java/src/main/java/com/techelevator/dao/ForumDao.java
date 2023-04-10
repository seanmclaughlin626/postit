package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {
    List<Forum> getForums();

    Forum getForumById(int id);

    boolean createForum(Forum forum);
}
