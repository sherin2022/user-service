package com.example.demo.exception;


public class CustomCreateUserException extends RuntimeException{
    public CustomCreateUserException(String s) {
        super(s);
    }
}