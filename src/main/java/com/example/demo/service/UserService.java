package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;

import java.util.List;

public interface UserService {

    UserDto createUser(UserRequest userRequest);
    List<UserDto> getUsers(Integer page, Integer size);
    public UserDto getUserDetails(String id);
    String deleteUser(String id);
    UserDto getUserDetailsByEmail(String emailId);
    UserDto updateUser(UserRequest userRequest, String userId);

}
