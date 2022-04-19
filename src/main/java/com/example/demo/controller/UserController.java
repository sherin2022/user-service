package com.example.demo.controller;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;


@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    //To add a new User record.
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest),HttpStatus.CREATED);
    }

    //To get a new User
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "size", required = false) Integer size) {
        return new ResponseEntity<>(userService.getUsers(page,size), HttpStatus.FOUND);
    }
    //To get a user with a particular userId

    //Changing the postedByUser to return String and not UserDto to check feign. changes 4
    @GetMapping("/{userId}")
    public UserDto getUserDetails(@PathVariable("userId") String userId,@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size) {
        return userService.getUserDetails(userId);
    }
    //To update a user record.
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest userRequest, @PathVariable("userId") String userId,@RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "size", required = false) Integer size){
        return new ResponseEntity<>(userService.updateUser(userRequest  , userId),HttpStatus.FOUND);
    }

    //To delete a user record
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.FOUND);
    }


    @GetMapping("/getUserByEmail/{emailId}")
    public UserDto getUserDetailsByEmail(@PathVariable("emailId") String emailId){
        return userService.getUserDetailsByEmail(emailId);
    }






}









