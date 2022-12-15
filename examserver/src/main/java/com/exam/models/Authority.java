package com.exam.models;


import org.springframework.security.core.GrantedAuthority;

//STEP 12 :b
public class Authority implements GrantedAuthority {


    private  String authority;

    //constructor
    public Authority(String authority) {
        this.authority = authority;
    }
//This function is used by spring security internally .
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
