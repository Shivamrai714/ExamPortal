package com.exam.services.impl_classes;

import com.exam.models.User;
import com.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//STEP 13 a\

@Component
public class UserDetailsServiceImpl  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Now we need to load the user , and return the object of user itself since it implements UserDetails already.

        User user = this.userRepository.findUserByUsername(username);

        if (user == null) {
            System.out.println("No user found");
            throw new UsernameNotFoundException("No user Found !! ");
        }
        return user;
    }
}
