package com.exam.controllers;


import com.exam.config.JwtUtil;
import com.exam.helper.UserNotFoundException;
import com.exam.models.JwtRequest;
import com.exam.models.JwtResponse;
import com.exam.models.User;
import com.exam.services.impl_classes.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

//to genrate the token


@RestController
@CrossOrigin("*")
public class AuthenticateController
{

    //WE need to use   @AuthenticationManager bean    , so make bean of  in MySecitryConfig class.

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    //api to generate the token after below coding.

    //generate Token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
try{
       //call the below function to authenticate
    authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

}
catch (UserNotFoundException e){
    e.printStackTrace();
    throw new Exception("User not found");

}

//authenticated

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
           String token = this.jwtUtil.generateToken(userDetails);
           return ResponseEntity.ok(new JwtResponse(token));

    }




    private  void authenticate(String username,String password) throws Exception {
        try {

           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }
        catch (DisabledException e)
        {
            throw new Exception("USER Disabled " + e.getMessage());
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Invalid Credientitals " + e.getMessage());
        }




    }


    //VIdeo  17  // return the detials of curren tuser needed for Angular
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal)
    {
     return ( (User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }


}
