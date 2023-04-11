package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private int commentId;
    private int authorId;
    private String content;
    private int postId;
    private LocalDateTime timeCreated;
    private String timeCreatedFormatted;

    public Comment() {
        this.timeCreated = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        this.timeCreatedFormatted = LocalDateTime.now().format(formatTime);
    }

    public Comment(int commentId, int authorId, String content, int postId) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.content = content;
        this.postId = postId;
        this.timeCreated = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        this.timeCreatedFormatted = LocalDateTime.now().format(formatTime);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public String getTimeCreatedFormatted() {
        return timeCreatedFormatted;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeCreatedFormatted(String timeCreatedFormatted) {
        this.timeCreatedFormatted = timeCreatedFormatted;
    }
}
