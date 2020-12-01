package com.rezahdrm.cms.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Post extends HistoricalColumn {

    private String title;
    private String slug;
    private String description;
    private String metaDescription;
    private String metaKeyword;
    private Status status;
    private Long userId;
    private Long photoId;
    private Long categoryId;
    private User user;
    private Photo photo;
    private Category category;
    private List<Comment> comments;

    @NotBlank(message = "عنوان مطلب را وارد نمایید")
    @Size(min = 5,max = 255,message = "عنوان باید 5 الی 255 کاراکتر باشد")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    @Enumerated(value = EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "photo_id")
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Column(name = "category_id")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(insertable = false, updatable = false)
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "post")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", metaKeyword='" + metaKeyword + '\'' +
                ", status=" + status +
                /*", userId=" + userId +
                ", photoId=" + photoId +
                ", categoryId=" + categoryId +
                ", user=" + user +
                ", photo=" + photo +
                ", category=" + category +
                ", comments=" + comments +*/
                '}';
    }

    public enum Status {
        ACTIVATE, DEACTIVATE
    }
}
