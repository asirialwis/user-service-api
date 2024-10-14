package com.example.crudapp.controller;

import com.example.crudapp.dto.UserRequest;
import com.example.crudapp.dto.UserResponse;
import com.example.crudapp.model.User;
import com.example.crudapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class


UserController {

    private final UserService userService;

    @GetMapping
    public List <UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable String id){
        return userService.findUserById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserRequest userRequest){
        userService.updateUser(id , userRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
