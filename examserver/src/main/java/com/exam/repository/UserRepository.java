package com.exam.repository;

//step 4 a)

import com.exam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//one interface can extends the another  interface.
public interface UserRepository  extends JpaRepository<User,Long>
{
   // this methed is implemented by spring itself.
    public  User findUserByUsername(String username);


}
