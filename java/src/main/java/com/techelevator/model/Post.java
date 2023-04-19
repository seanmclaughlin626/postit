package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Post {
   private int postId;
   private int forumId;
   private int author;
   private String title;
   private String content;
   private int upvoteScore;
   private LocalDateTime timeCreated;
   private String timeFormatted;
   private LocalDateTime lastInteraction;
   private String authorName;
   private String url;
   private String forumName;

   public Post(){}

   public Post(int postId, int author, String title, String content, int upvoteScore, String url) {
       this.postId = postId;
       this.author = author;
       this.title = title;
       this.content = content;
       this.upvoteScore = upvoteScore;
       this.url = url;
   }

   public int getPostId() {
       return postId;
   }

   public void setPostId(int postId) {
       this.postId = postId;
   }

   public int getAuthor() {
       return author;
   }

   public void setAuthor(int author) {
       this.author = author;
   }

   public String getTitle() {
       return title;
   }

   public void setTitle(String title) {
       this.title = title;
   }

   public String getContent() {
       return content;
   }

   public void setContent(String content) {
       this.content = content;
   }

   public int getUpvoteScore() {
       return upvoteScore;
   }

   public void setUpvoteScore(int upvoteScore) {
       this.upvoteScore = upvoteScore;
   }

   public LocalDateTime getTimeCreated() {
       return timeCreated;
   }

   public void setForumId(int forumId) {
       this.forumId = forumId;
   }

   public void setTimeCreated(LocalDateTime timeCreated) {
       this.timeCreated = timeCreated;
   }

   public int getForumId() {
       return forumId;
   }

    public String getTimeFormatted() {
        return timeFormatted;
    }

    public void setTimeFormatted(String timeFormatted) {
        this.timeFormatted = timeFormatted;
    }

    public LocalDateTime getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(LocalDateTime lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }
}






