package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.repositories.UserRepository;
import com.greenfoxacademy.zerothweekproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;

  @Autowired
  public MyUserDetailsService(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new UserDetailsImpl(userRepository.findByName(username));
  }
}
