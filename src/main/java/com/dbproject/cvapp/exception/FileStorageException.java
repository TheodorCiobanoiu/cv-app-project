package com.dbproject.cvapp.exception;

public class FileStorageException extends Exception {
    public FileStorageException(String fileName){
        super("Could not store file " + fileName + "Please try again.");
    }
}
