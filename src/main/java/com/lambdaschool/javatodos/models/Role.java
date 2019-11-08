package com.lambdaschool.javatodos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long roleid;

    @Column(nullable = false,
            unique = true)
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
