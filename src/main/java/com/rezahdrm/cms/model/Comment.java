package com.rezahdrm.cms.model;

import javax.persistence.*;

@Entity
public class Comment extends HistoricalColumn {
    private Long postId;
    private Post post;
    private Long parentId;
    private String description;
    private Status status;

    @Column(name = "post_id")
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    enum Status {
        APPROVED, REJECTED
    }
}
