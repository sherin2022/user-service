package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid  @RequestBody User user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.FOUND);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") String userId) {
        return new ResponseEntity<User>(userService.getUserDetails(userId), HttpStatus.FOUND);
    }
    @PutMapping("users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") String userId){
        return new ResponseEntity<User>(userService.updateUser(user, userId),HttpStatus.FOUND);
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity<User>(userService.deleteUser(userId),HttpStatus.FOUND);
    }


}

