package com.example.demo.controller;
import com.example.demo.dto.UserWithoutPassword;
import com.example.demo.dto.UserRequest;
import com.example.demo.exception.UserIdMismatchException;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

import static com.example.demo.constantException.UserConstantException.USERIDMISMATCH;


@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    //To add a new User record.
    @PostMapping

    public ResponseEntity<UserWithoutPassword> createUser(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest),HttpStatus.CREATED);
    }

    //To get a new User
    @GetMapping
    public ResponseEntity<List<UserWithoutPassword>> getUsers(@RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new ResponseEntity<>(userService.getUsers(page,pageSize), HttpStatus.OK);
    }
    //To get a user with a particular userId

    //Changing the postedByUser to return String and not UserDto to check feign. changes 4
    @GetMapping("/{userId}")
    public UserWithoutPassword getUserDetails(@PathVariable("userId")String userId){

        return userService.getUserDetails(userId);
    }
    //To update a user record.
    @PutMapping("/{userId}")
    public ResponseEntity<UserWithoutPassword> updateUser(@RequestBody UserRequest userRequest, @PathVariable("userId") String userId){

        if(!(userRequest.getId().equals(userId))){
            throw new UserIdMismatchException(USERIDMISMATCH);
        }
        return new ResponseEntity<>(userService.updateUser(userRequest  , userId),HttpStatus.FOUND);
    }

    //To delete a user record
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.FOUND);
    }


    @GetMapping("/getUserByEmail/{emailId}")
    public UserWithoutPassword getUserDetailsByEmail(@PathVariable("emailId") String emailId){
        return userService.getUserDetailsByEmail(emailId);
    }
}









