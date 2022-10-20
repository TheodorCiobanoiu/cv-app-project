package com.dbproject.cvapp.exception;

public class MyFileNotFoundException extends Exception {
    public MyFileNotFoundException(Integer id){
        super("Could not find file with id "+ id);
    }
}
