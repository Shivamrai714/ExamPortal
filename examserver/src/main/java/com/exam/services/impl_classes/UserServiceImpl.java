package com.exam.services.impl_classes;

//STEP : 5

import com.exam.helper.UserFoundException;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;   // spring it self manages to create the obj from the implementation classes of the following interfaces.

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        // check if the given user already exists or not.
        User local=this.userRepository.findUserByUsername(user.getUsername());
        if(local!=null)
        {
            System.out.println("User already present !! ");
            //throw new Exception("User already present !! Bye ");
         //VIDEO 19 :create helper folder and exceptions in it.
           throw new UserFoundException();
        }
        else {
            // create the  user and save it ,
            // we need to extract all the roles present in the user Set and save them.

            //Step 5 b  // save all roles to the database
          for(UserRole ur :  userRoles)
          {
              roleRepository.save(ur.getRole());
          }

         user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }

        return local;
    }

    //STEP 7 d.
    // getting user by username
    @Override
    public User getUser(String username) {
        //using repository to access the user
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }


}
