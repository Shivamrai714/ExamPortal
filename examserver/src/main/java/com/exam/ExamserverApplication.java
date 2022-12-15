package com.exam;

import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//STEP 6 : creating a user and role and setting the data to userRole to check working,
@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner  {


	//step 7
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

/*

//ONLY FOR CHECKING , ALSO CAN ADD ADMIN FROM HERE DIRECTLY   :::::::::::




   try{
		System.out.println("Temporary Running. . . . ");

		// step 6 a :  checking service

// create user object
		User user = new User();

		user.setFirstname("Shivam");
		user.setLastname("Rai");
		user.setEmail("shivam714@gmail.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("Shivamrai714@"));
		user.setUsername("Shivamrai714@");
		user.setPhone("8319860549");
		user.setProfile("default.png");
		user.setEnabled(true);

// create role object
		Role role1= new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");

//create UserRole object.
		UserRole userRole = new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);

		// create set to store the userRoles
		Set<UserRole> userRoleSet= new HashSet<>();
		userRoleSet.add(userRole);

		// save the data using userservice , but first autowire userservice.

		User user1   = this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());
}catch(UserFoundException e )
{
e.printStackTrace();
System.out.println("Username Already Exits ...");
}

*/


	}
}
