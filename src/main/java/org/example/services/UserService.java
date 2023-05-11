package org.example.services;


import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    public void save(User user){
        userRepository.save(user);
    }
    public User findByUsername(String username){
        return  userRepository.findByUsername(username);
    }
}
