package org.example.serverapp.exception;

public class MyDataAccessException extends RuntimeException {

    public MyDataAccessException(String message) {
        super(message);
    }
}
