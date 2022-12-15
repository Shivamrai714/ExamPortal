package com.exam.helper;

public class UserFoundException extends  Exception{


    public UserFoundException()
    {
        super("User with this username already exits in DB !! Try with other.");
    }
    public  UserFoundException(String msg)
    {
        super(msg);
    }

}
