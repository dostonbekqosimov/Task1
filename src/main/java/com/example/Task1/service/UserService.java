package com.example.Task1.service;

import com.example.Task1.entity.User;
import com.example.Task1.handler.ResourceNotFoundException;
import com.example.Task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public ResponseEntity<User> getUserByUsername(String username){

        List<User> users = userRepository.findAll();
        User user = userRepository.findByUsername(username) .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // User not found (optional: return specific status code or message)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public User registerNewUser(User user) {
      // hech narsa tekshirilinmayabdi
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
