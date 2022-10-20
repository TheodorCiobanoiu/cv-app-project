package com.dbproject.cvapp.exception;

public class NoAuthorizationException extends Exception {
    public NoAuthorizationException(){
        super("You do not have the authorization to acces this endpoint.");
    }
}
