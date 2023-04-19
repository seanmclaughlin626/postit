package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.model.FavoriteForumDto;
import com.techelevator.model.Forum;
import com.techelevator.model.User;
import com.techelevator.services.ForumService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ForumController {

    private ForumDao forumDao;
    private final ForumService forumService;

    public ForumController(ForumDao forumDao, ForumService forumService) {
        this.forumDao = forumDao;
        this.forumService = forumService;
    }

    @GetMapping("/forums")
    public List<Forum> getForumsController(){
        return forumDao.getForums();
    }

    @GetMapping("/forums/{id}")
    public Forum getForumByIdController(@PathVariable int id){
        return forumDao.getForumById(id);
    }

    @GetMapping("/forums/{forumId}/mods")
    public List<Integer> getModIdsByForumIdControl(@PathVariable int forumId){
        return forumDao.getModIdsByForumId(forumId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/forums")
    @ResponseStatus(HttpStatus.CREATED)
    public int createForumController(@RequestBody Forum forum, Principal principal){
        return forumService.createForumService(forum, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/forums/{forumId}/mods")
    public void promoteUserToModController(@RequestBody User user, @PathVariable int forumId){
        forumService.promoteUserToMod(user.getUsername(), forumId);
    }

    @GetMapping("/favorite-forum-ids")
    public List<Integer> getFavoriteForumIdsControl(@RequestParam("id") int id){
        return forumDao.getFavoriteForumIds(id);
    }

    @GetMapping("/favorite-forums")
    public List<Forum> getFavoriteForumsControl(@RequestParam("id") int id){
        return forumDao.getFavoriteForums(id);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/favorite-forums")
    public void addFavoriteForumControl(@RequestBody FavoriteForumDto favoriteForumDto){
        forumDao.addFavoriteForum(favoriteForumDto.getForumId(), favoriteForumDto.getUserId());
    }

    @GetMapping("/forum-search")
    public List<Forum> getForumsBySearchQueryControl(@RequestParam("searchInput") String searchInput){
        return forumDao.getForumsBySearchQuery(searchInput);
    }
}
