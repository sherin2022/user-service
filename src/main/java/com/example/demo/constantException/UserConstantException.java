package com.example.demo.constantException;

public final class UserConstantException {

    private UserConstantException() {
        // restrict instantiation
    }

    public static final String DELETEUSER = "User successfully deleted"  ;
    public static final String USERNOTFOUND = "User not found with id : ";
    public static final String NOUSERFOUND = "No user data available : ";
}

