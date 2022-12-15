package com.exam.controllers;

import com.exam.helper.UserFoundException;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
   //To handle the angular http request call as both application are running on different porst so use this.
public class UserController {

    //STEP 7
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception   // request body is used to fetch the data sent in json format from postman.
    {
        // to save this user we need userservice,
        //we need to save user with set of UserRole

        user.setProfile("default.png");
        //STEP 19 :Encrypt PAssword


       user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Role role= new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole= new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        //Now add the respective roles in the set and save it along with the user.

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);

        return this.userService.createUser(user, roles );

    }



    // getting user
    @GetMapping("/{username}")   // the dynamic values passed through the  postman will be collected in the path varible.
    public User getUser(@PathVariable("username") String username)
    {
        return  this.userService.getUser(username);

    }

    //STEP 7  d : After creating the method in userService which users the userRepo internally to delete user

    //delete user by id
    @DeleteMapping("/{userId}")
    public  void deleteUser(@PathVariable("userId") Long userId)
    {
        this.userService.deleteUser(userId);
    }


    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

}
