package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    //public ResponseEntity<User> createUser(User user);
    UserDTO createUser(UserRequest userRequest);
    List<UserDTO> getUsers();
    //List<UserDTO> getUsers(int page, int pageSize);
    public UserDTO getUserDetails(String id);
    UserDTO updateUser(UserRequest userRequest,String userId);
    String deleteUser(String id);


}
