package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequest;
import com.example.demo.exception.CustomCreateUserException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid  @RequestBody UserRequest userRequest){
        try {
            return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new CustomCreateUserException("Syntax Error");
        }
    }
/*
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize){
        return new ResponseEntity<>(userService.getUsers(page, pageSize),HttpStatus.OK);
    }*/
@GetMapping("/users")
public ResponseEntity<List<UserDTO>> getUsers() {
    return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
}
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.getUserDetails(userId), HttpStatus.FOUND);
    }
    @PutMapping("users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserRequest userRequest, @PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.updateUser(userRequest, userId),HttpStatus.FOUND);
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.FOUND);
    }


}

