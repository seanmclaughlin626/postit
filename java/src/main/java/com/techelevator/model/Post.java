package com.techelevator.model;

import java.util.List;

public class Post {
    int postId;
    int author;
    String title;
    String content;
    int upvoteScore;

    public Post(){ }

    public Post(int postId, int author, String title, String content, int upvoteScore) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.upvoteScore = upvoteScore;
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
}
