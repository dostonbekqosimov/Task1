package com.example.Task1.service;

import com.example.Task1.entity.User;
import com.example.Task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public ResponseEntity<User> getUserByUsername(String username){

        List<User> users = userRepository.findAll();
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // User not found (optional: return specific status code or message)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
