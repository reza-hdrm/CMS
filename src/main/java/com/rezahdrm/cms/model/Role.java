package com.rezahdrm.cms.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role extends HistoricalColumn {
    private String name;
    private List<Long> usersId;
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinTable(name = "role_user",joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<Long> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Long> usersId) {
        this.usersId = usersId;
    }

    @ManyToMany
    @JoinTable(name = "role_user",joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'';
    }
}
