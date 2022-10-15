package com.dbproject.cvapp.exception;

public class NoUserException extends Exception{
    public NoUserException(Integer id){
        super("The user with id: " + id + " does not exist!");
    }

    public NoUserException(String username){
        super("The user with username: " + username + " does not exist");
    }
}
