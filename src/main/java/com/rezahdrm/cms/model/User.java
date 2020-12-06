package com.rezahdrm.cms.model;

import javax.persistence.*;
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
    private List<Long> rolesId;
    private List<Role> roles;

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

    @Enumerated(value = EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "photo_id")
    public Long getPhotoId() {
        return PhotoId;
    }

    public void setPhotoId(Long photoId) {
        PhotoId = photoId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
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

    @ManyToMany(mappedBy = "usersId", targetEntity = Role.class)
    public List<Long> getRolesId() {
        return rolesId;
    }

    public void setRolesId(List<Long> rolesId) {
        this.rolesId = rolesId;
    }

    @ManyToMany(mappedBy = "users")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
