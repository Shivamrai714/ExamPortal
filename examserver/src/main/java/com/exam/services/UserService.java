package com.exam.services;

//step 4 c)  Creating the service .

import com.exam.models.User;
import com.exam.models.UserRole;

import java.util.Set;

public interface UserService {
    // create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //STEP 7.C
    // get user by username         -- defination in the implementation class.
    public User getUser(String username);

    // 7 d    : we are creating the end points to access the api
    //delete user by id
    public  void deleteUser(Long userId);



}
