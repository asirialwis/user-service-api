package com.example.crudapp.service;

import com.example.crudapp.dto.UserRequest;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User>getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User>getUserById(String id){
        return userRepository.findById(id);
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
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
