package com.rezahdrm.cms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class User extends HistoricalColumn {
    private String name;
    private String email;
    private Timestamp emailVerifiedAt;
    private String password;
    private Status status;
    private Long PhotoId;
    private Photo photo;
    private String rememberToken;
    private List<Post> posts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Timestamp emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getPhotoId() {
        return PhotoId;
    }

    public void setPhotoId(Long photoId) {
        PhotoId = photoId;
    }

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "photo_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    @OneToMany(mappedBy = "user")
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    enum Status {
        ACTIVATE, DEACTIVATE, BLOCK
    }
}