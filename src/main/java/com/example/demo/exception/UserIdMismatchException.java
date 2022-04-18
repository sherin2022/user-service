package com.example.demo.exception;

public class UserIdMismatchException extends RuntimeException{
    public UserIdMismatchException(String s){
        super(s);
    }
}
