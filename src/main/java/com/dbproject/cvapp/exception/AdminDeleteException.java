package com.dbproject.cvapp.exception;

public class AdminDeleteException extends Exception {
    public AdminDeleteException(){
        super("You cannot delete an admin from database. If you wish to do so, please send the request directly from MySQL");
    }
}
