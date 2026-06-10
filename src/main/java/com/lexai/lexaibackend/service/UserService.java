package com.lexai.lexaibackend.service;

import com.lexai.lexaibackend.model.User;
import com.lexai.lexaibackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        Optional<User> existing=userRepository.findByEmail(user.getEmail());
        if(existing.isPresent()){
            throw new RuntimeException("User already present");
        }
        return  userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

}
