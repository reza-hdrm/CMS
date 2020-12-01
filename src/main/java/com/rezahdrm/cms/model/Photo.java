package com.rezahdrm.cms.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Photo extends HistoricalColumn {
    private String path;
    //private User user;
    private Post post;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*@OneToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @OneToOne(mappedBy = "photo")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
