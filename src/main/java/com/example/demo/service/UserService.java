package com.example.demo.service;


import com.example.demo.dto.UserWithoutPassword;
import com.example.demo.dto.UserRequest;

import java.util.List;

public interface UserService {

    UserWithoutPassword createUser(UserRequest userRequest);
    List<UserWithoutPassword> getUsers(Integer page, Integer pageSize);
    public UserWithoutPassword getUserDetails(String id);
    String deleteUser(String id);
    UserWithoutPassword getUserDetailsByEmail(String emailId);
    UserWithoutPassword updateUser(UserRequest userRequest, String userId);

}
