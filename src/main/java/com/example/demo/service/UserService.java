package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(UserRequest userRequest);
    List<UserDto> getUsers();
    public User getUserDetails(String id);
    User updateUser(UserRequest user,String userId);
    String deleteUser(String id);
    UserDto getUserById(String userId);
}
