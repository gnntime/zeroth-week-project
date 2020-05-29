package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.modells.daos.User;
import com.greenfoxacademy.zerothweekproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void registerUser(String name, String password) {
    User user = new User(name, passwordEncoder.encode(password));
    userRepository.save(user);
  }

}
