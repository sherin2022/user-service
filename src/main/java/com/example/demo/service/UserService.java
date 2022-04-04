package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserRequest userRequest);
    List<UserDto> getUsers(Integer page, Integer size);
    public UserDto getUserDetails(String id);
    User updateUser(UserRequest user,String userId);
    String deleteUser(String id);
    UserDto getUserById(String userId);
    //String getUserDetailsByEmail(String emailId);
    //Changing the postedByUser to return String and not UserDto to check feign. changes 5

    //String testFeign(String postId);
    UserDto getUserDetailsByEmail(String emailId);
}
