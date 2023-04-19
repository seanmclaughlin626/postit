package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface ForumDao {
    List<Forum> getForums();

    Forum getForumById(int id);

    int createForum(Forum forum, int id);

    List<Integer> getModIdsByForumId(int forumId);

    void promoteUserToMod(int userId, int forumId);

    List<Integer> getFavoriteForumIds(int userId);

    void addFavoriteForum(int forumId, int userId);

    List<Forum> getFavoriteForums(int userId);

    List<Forum> getForumsBySearchQuery(String searchInput);
}
