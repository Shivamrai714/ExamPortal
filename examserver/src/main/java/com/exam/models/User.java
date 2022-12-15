package com.exam.models;

//STEP :2  make user entity


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private  String phone;
    private boolean enabled=true;
    private String profile;


    //STEP : 3 a : setting :::: 1 to  many relationship using set in UserRoles table
    //user has many roles
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")   //user is var in UserRole , to allow only 1 table to be created after mapping.
    @JsonIgnore
    private Set<UserRole> userRoles=new HashSet<>();


    public User()
    {

    }

    public User(long id, String username, String password, String firstname, String lastname, String email, String phone, boolean enabled, String profile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.profile = profile;
    }

    // getter and setters


    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    //Methods to Override for UserDetails.

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    //VERY important Method ::::::::::::::::::::::
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        //STEP 12 a : create set of Authorities

        Set<Authority> set =  new HashSet<>();
       // 12 c: Now we will set the roles in the collection , so we use the Set UserRoles to get userRole obj which intenally conatiains the roles available in Authoirities.
        // And we also keep on adding differnt roles in set using the Authority object.

        this.userRoles.forEach(  userRole -> {
            set.add(   new Authority(userRole.getRole().getRoleName()));
                }
                );


////////////////////////////////////////////////
        return set;
    }


}
