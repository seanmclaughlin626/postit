package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Post {
    private int postId;
    private int author;
    private String title;
    private String content;
    private int upvoteScore;
    private String timeCreated;



    public Post(){ }

    public Post(int postId, int author, String title, String content, int upvoteScore) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.upvoteScore = upvoteScore;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        this.timeCreated = LocalDateTime.now().format(formatTime);
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

    public String getTimeCreated() {
        return timeCreated;
    }
}
