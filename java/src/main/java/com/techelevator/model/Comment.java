package com.techelevator.model;

public class Comment {
    private int commentId;
    private int authorId;
    private String content;
    private int postId;

    public Comment() {
    }

    public Comment(int commentId, int authorId, String content, int postId) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.content = content;
        this.postId = postId;
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
}
