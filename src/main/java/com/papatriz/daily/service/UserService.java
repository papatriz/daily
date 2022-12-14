package com.papatriz.daily.service;

import com.papatriz.daily.entity.User;
import com.papatriz.daily.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
  //  private final PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
      //  this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUserList(){

        return  userRepository.findAll();
    }

    public User saveUser(User user){

      //  user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.deleteById(user.getId());
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getTestUser() {
        Optional<User> testUser = userRepository.findById(UUID.fromString("c0a80202-8510-1afd-8185-10eb54550000"));
        logger.info("TEST USER: " + testUser.isPresent());
        return testUser;
    }

}
