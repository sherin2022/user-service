package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    //public ResponseEntity<User> createUser(User user);
    User createUser(User user);
    List<User> getUsers();
    public User getUserDetails(String id);
    User updateUser(User user,String userId);
    User deleteUser(String id);


}
