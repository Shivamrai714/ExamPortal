package com.exam.models;

import javax.persistence.*;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    //Step 3 b  : UserRole have single user associated with it.

   @ManyToOne(fetch = FetchType.EAGER)
    private  User user;

   // 3 d  map the role
   @ManyToOne
    private Role role;


   // constructor and getters and setters.
    public UserRole() {
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
