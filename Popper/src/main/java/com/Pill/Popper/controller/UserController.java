package com.Pill.Popper.controller;

import java.util.List;

import com.Pill.Popper.dao.entity.User;
import com.Pill.Popper.dao.service.UserService;
import com.Pill.Popper.exception.ResourceNotFoundException2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        super();
        this.userService = userService;
    }

    // get users

    @GetMapping("users")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    // get user by id
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long userId) throws ResourceNotFoundException2 {
        return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }
    
    
    // save user
    @PostMapping("users")
    public User createUser(@RequestBody User user){
        return this.userService.save(user);
    }

    // update user
    @PutMapping("users/{id}")
    public ResponseEntity<User>updateUser(@PathVariable("id") long userId, @RequestBody User user) throws ResourceNotFoundException2 {
        return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK );
    }

    
    // delete user
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userId) throws ResourceNotFoundException2 {
        userService.deleteUserById(userId);

        return new ResponseEntity<String>("User details deleted successfully!", HttpStatus.OK);

    }

}