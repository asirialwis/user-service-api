package com.example.crudapp.service;

import com.example.crudapp.dto.UserRequest;
import com.example.crudapp.dto.UserResponse;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserService {

    private final UserRepository userRepository;

    //Get All Users
    public List<UserResponse>getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    public UserResponse findUserById(String id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(NOT_FOUND,"User Not Found"));
        return mapToUserResponse(user);
    }



    public void  createUser(UserRequest userRequest){
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .build();

        userRepository.save(user);
    }
    public User updateUser(String id, User user){
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }



    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

}
