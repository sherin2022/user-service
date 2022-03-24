package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    //To add a new User record.
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<UserDto> (userService.createUser(userRequest),HttpStatus.OK);
    }

    //To get a new User
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<List<UserDto>>(userService.getUsers(), HttpStatus.FOUND);
    }
    //To get a user with a particular userId
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") String userId) {
        return new ResponseEntity<User>(userService.getUserDetails(userId), HttpStatus.FOUND);
    }
    //To update a user record.
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest userRequest, @PathVariable("userId") String userId){
        return new ResponseEntity<User>(userService.updateUser(userRequest  , userId),HttpStatus.FOUND);
    }

    //To delete a user record
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity<String>(userService.deleteUser(userId),HttpStatus.FOUND);
    }






}






